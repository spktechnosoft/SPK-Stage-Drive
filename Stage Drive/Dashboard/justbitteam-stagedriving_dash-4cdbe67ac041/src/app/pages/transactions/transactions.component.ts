import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { TransactionsService, TransactionDTO, StgdrvResponseDTO } from '../../api';
import { CustomServerDataSource } from '../server.data-source';
import { HttpClient, HttpHeaders, HttpParams,
  HttpResponse, HttpEvent } from '@angular/common/http';
import { Location, LocationStrategy, PathLocationStrategy, DatePipe } from '@angular/common';
import { Router } from "@angular/router";
import { UserRenderComponent } from "../users/user-render.component";
import { environment } from '../../../environments/environment';
import { NbToastrService } from '@nebular/theme';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'ngx-users',
  templateUrl: './transactions.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `],
  providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}]
})
export class TransactionsComponent {

  statuses = {
    'processed': 'Processata',
    'pending': 'Appesa',
    'refunded': 'Rimborsata',
    'deleted': 'Eliminata'
  };

  providers = {
    'cash': 'Contanti',
    'paypal': 'PayPal',
  };

  loading = false;

  selectedTransactions: any[];

  settings = {
    selectMode: 'multi',
    pager : {
      perPage:10,
      display: true
    },
    hideSubHeader: false, 
    actions:{add:false, edit:false, delete:true},
    mode: 'external',
    //attr:{class:"table table-hover table-striped"},  //use bootstrap zebra style
    add: {
      addButtonContent: '<i class="nb-plus"></i>',
      createButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    edit: {
      editButtonContent: '<i class="nb-edit"></i>',
      saveButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    delete: {
      deleteButtonContent: '<i class="nb-trash"></i>',
      confirmDelete: true,
    },
    /**
     * 
      accountFrom: {id: "15426500890499602162", created: "2018-11-30T16:53:12.000Z", modified: "2018-11-30T16:53:12.000Z",…}
      accountTo: {id: "15405801789888601659", created: "2018-11-06T17:54:42.000Z", modified: "2018-11-06T17:54:42.000Z",…}
      amount: 10
      created: "2019-06-13T09:37:52.000Z"
      event: {id: "15408180286222664037", created: "2018-11-09T11:58:51.000Z", modified: "2018-11-09T11:58:51.000Z",…}
      fee: 10.72
      id: "15594719693986905484"
      modified: "2019-06-13T09:37:52.000Z"
      payedAt: "2019-06-13T09:37:54.000Z"
      provider: "paypal"
      providerToken: "fake-valid-nonce"
      refundedAt: "2019-06-13T09:40:25.000Z"
      ride: {id: "15576576314325590037", created: "2019-05-23T09:38:54.000Z", modified: "2019-05-23T09:38:54.000Z",…}
      status: "deleted"
      totalAmount: 20.72
     */
    columns: {
      id: {
        title: 'ID',
        type: 'text',
      },
      status: {
        title: 'Stato',
        type: 'text',
        filter: {
          type: 'list',
          config: {
            selectText: 'Scegli',
            list: [
              { value: 'processed', title: 'Processato' },
              { value: 'pending', title: 'Appeso' },
              { value: 'refunded', title: 'Rimborsato' }
            ],
          },
        },
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            return this.statuses[value];
          }
        }
      },
      accountFrom: {
        title: 'Passeggero',
        type: 'custom',
        filter: false,
        renderComponent: UserRenderComponent,
      },
      accountTo: {
        title: 'Autista',
        type: 'custom',
        renderComponent: UserRenderComponent,
        filter: false,
      },
      ride: {
        title: 'Passaggio',
        type: 'text',
        filter: false,
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            console.log('ride', value);
            return value.id;
          }
        }
      },
      provider: {
        title: 'Metodo pagamento',
        type: 'text',
        filter: {
          type: 'list',
          config: {
            selectText: 'Scegli',
            list: [
              { value: 'cash', title: 'Contanti' },
              { value: 'paypal', title: 'PayPal' }
            ],
          },
        },
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            return this.providers[value];
          }
        }
      },
      amount: {
        title: 'Importo',
        type: 'text',
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            return value + ' €';
          }
        }
      },
      fee: {
        title: 'Fee',
        type: 'text',
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            return value + ' €';
          }
        }
      },
      providerFee: {
        title: 'Provider fee',
        type: 'text',
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            return value + ' €';
          }
        }
      },
      totalAmount: {
        title: 'Importo + Fee',
        type: 'text',
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            return value + ' €';
          }
        }
      },
      refundedAmount: {
        title: 'Importo rimborsato',
        type: 'text',
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            return value + ' €';
          }
        }
      },
      payedAt: {
        title: 'Data pagamento',
        type: 'text',
        filter: false,
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            let pipe = new DatePipe('it-IT'); // Use your own locale
            return pipe.transform(value, 'short');
          }
        }
      },
      refundedAt: {
        title: 'Data rimborso',
        type: 'text',
        filter: false,
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            let pipe = new DatePipe('it-IT'); // Use your own locale
            return pipe.transform(value, 'short');
          }
        }
      },
    },
  };

  source: ServerDataSource;

  constructor(private transactionsService: TransactionsService, 
              private http: HttpClient,
              private location: Location,
              private router: Router,
              private toastrService: NbToastrService,
              private sanitizer: DomSanitizer) {
    this.source = new CustomServerDataSource(http, {
      endPoint: environment.endpoint+'/v1/transactions?size=true',
      pagerLimitKey:"limit",
      pagerPageKey:"page",
      sortDirKey: "order",
      sortFieldKey: "sort",
      totalKey: 'size',
      dataKey: 'data'
    });
  }

  onEdit(event): void {
    console.log('event', event);
    //this.location.go('/users/'+event.data.id);
    this.router.navigate(['pages/users', event.data.id]).then( (e) => {
      if (e) {
        console.log("Navigation is successful!");
      } else {
        console.log("Navigation has failed!");
      }
    });
  }

  onDelete(event): void {
    if (window.confirm('Sei sicuro di voler procedere?')) {
      this.transactionsService.deleteTransaction(event.data.id).subscribe((res: StgdrvResponseDTO) => {
        console.log('Res', res)
        this.source.refresh();
        this.loading = false;
  
        this.toastrService.success('Success', res.message);
      }, err => {
        console.error("Unable to delete transaction", err);
        this.toastrService.danger('['+err.error.code+'] '+err.error.message, 'Impossibile eliminare la transazione');
        this.loading = false;
      });
    } else {
      this.loading = false;
    }
  }

  deleteBulk(): void {
    if (window.confirm('Sei sicuro di voler procedere?')) {
      this.loading = true;

      var tasks$ = [];
      this.selectedTransactions.forEach(transaction => {
        tasks$.push(this.transactionsService.deleteTransaction(transaction.id));
      });

      forkJoin(tasks$).subscribe(results => { 
        console.log(results);

        this.source.refresh();
        this.loading = false;

        this.toastrService.success('Success', 'Transazioni eliminati');
      }, err => {
        console.error("Unable to delete transaction", err);
        this.toastrService.danger('['+err.error.code+'] '+err.error.message, 'Impossibile eliminare le transazioni');
        this.loading = false;
      });

      // forkJoin(
      //   this._myService.makeRequest('Request One', 2000),
      //   this._myService.makeRequest('Request Two', 1000),
      //   this._myService.makeRequest('Request Three', 3000)
      // )
      // .subscribe(([res1, res2, res3]) => {
      //   this.propOne = res1;
      //   this.propTwo = res2;
      //   this.propThree = res3;
      // });

      // this.accountsService.deleteAccount(event.data.id).subscribe((res: StgdrvResponseDTO) => {
      //   console.log('Res', res)
      //   this.source.refresh();
      //   this.loading = false;
  
      //   this.toastrService.success('Success', res.message);
      // });
    } else {
      this.loading = false;
    }
  }

  transactionSelectRows(selectedList) {
    this.selectedTransactions = selectedList;
    console.log('Selected transactions', this.selectedTransactions);
  }
}
