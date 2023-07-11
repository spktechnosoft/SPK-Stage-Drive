import { Component, TemplateRef, ViewChild, ViewContainerRef } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { EventDTO, EventsService, StgdrvResponseDTO, ExportsService, ExportDTO, AccountsService, AccountDTO } from '../../api';
import { CustomServerDataSource } from '../server.data-source';
import { HttpClient, HttpHeaders, HttpParams,
  HttpResponse, HttpEvent } from '@angular/common/http';
import { Location, LocationStrategy, PathLocationStrategy, DatePipe } from '@angular/common';
import { Router } from "@angular/router";
import { UserRenderComponent } from "../users/user-render.component";
import { environment } from '../../../environments/environment';
import { NbToastrService, NbWindowService } from '@nebular/theme';
import { BundleService } from '../commons/bundle.service';
import { NbAuthService, NbAuthSimpleToken } from '@nebular/auth';
import { EventEditComponent } from './event-edit.component';
import { UserEditComponent } from '../users/user-edit.component';
import { UsersComponent } from '../users/users.component';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'ngx-events',
  templateUrl: './events.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `],
  providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}]
})
export class EventsComponent {

  @ViewChild('eventEdit', {  }) eventEditTemplate: TemplateRef<any>;

  statuses = {
    'draft': 'Bozza',
    'published': 'Pubblicato',
  };

  categories: any[] = [];
  categoriesKeys: any[] = [];

  loading = false;

  selectedEvents: any[];

  settings = {
    selectMode: 'multi',
    pager : {
      perPage:10,
      display: true
    },
    hideSubHeader: false, 
    actions:{add:false, edit:true, delete:true},
    mode: 'external',
    //attr:{class:"table table-hover"},  //use bootstrap zebra style
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
              { value: 'draft', title: 'Bozza' },
              { value: 'published', title: 'Pubblicato' }
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
      name: {
        title: 'Nome',
        type: 'text',
        filter: true,
      },
      category: {
        title: 'Categoria',
        type: 'text',
        filter: {
          type: 'list',
          config: {
            selectText: 'Scegli',
            list: this.categories,
          },
        },
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            let index = this.categoriesKeys.indexOf(value);
            if (index !== -1) {
              return this.categories[index].title;
            } else {
              return value;
            }
          }
        }
      },
      country: {
        title: 'Nazione',
        type: 'text',
        filter: false,
      },
      city: {
        title: 'Città',
        type: 'text',
        filter: false,
      },
      // town: {
      //   title: 'Città',
      //   type: 'text',
      //   filter: false,
      // },
      address: {
        title: 'Indirizzo',
        type: 'text',
        filter: false,
      },
      zipcode: {
        title: 'CAP',
        type: 'text',
        filter: false,
      },
      nride: {
        title: 'Passaggi',
        type: 'text',
        filter: false,
      },
      nlike: {
        title: 'Like',
        type: 'text',
        filter: false,
      },
      organizer: {
        title: 'Organizzatore',
        type: 'text',
        filter: false,
      },
      start: {
        title: 'Data inizio',
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
      finish: {
        title: 'Data fine',
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
      created: {
        title: 'Data creazione',
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
  bundle: any;

  constructor(private http: HttpClient,
              private location: Location,
              private router: Router,
              private eventsService: EventsService,
              private toastrService: NbToastrService,
              private sanitizer: DomSanitizer,
              private bundleService: BundleService,
              private exportsService: ExportsService,
              private accountsService: AccountsService,
              private authService: NbAuthService,
              private windowService: NbWindowService) {

    this.bundle = bundleService.bundle;
    var items = this.bundle['event-category'].items;
    items.forEach(item => {
      console.log('item', item);
      this.categories.push({
        'value': item.name,
        'title': item.description
      });
      this.categoriesKeys.push(item.name);
    });
    console.log('categories', this.categories);
    this.source = new CustomServerDataSource(http, {
      endPoint: environment.endpoint+'/v1/events?size=true',
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
    // this.windowService.open(
    //   this.eventEditTemplate,
    //   {
    //     title: 'Evento', 
    //     context: { 
    //       id: event.data.id
    //     },
    //     windowClass: 'custom-window'
    //   },
    // );
    window.open("/#/pages/events/"+event.data.id, "_blank");
    return;
    this.router.navigate(['pages/events', event.data.id]).then( (e) => {
      if (e) {
        console.log("Navigation is successful!");
      } else {
        console.log("Navigation has failed!");
      }
    });
  }

  onDelete(event): void {
    if (window.confirm('Sei sicuro di voler procedere?')) {
      this.eventsService.deleteEvent(event.data.id, '').subscribe((res: StgdrvResponseDTO) => {
        console.log('Res', res)
        this.source.refresh();
        this.loading = false;
  
        this.toastrService.success('Success', res.message);
      });
    } else {
      this.loading = false;
    }
  }

  deleteBulk(): void {
    if (window.confirm('Sei sicuro di voler procedere?')) {
      this.loading = true;

      var tasks$ = [];
      this.selectedEvents.forEach(event => {
        tasks$.push(this.eventsService.deleteEvent('', event.id));
      });

      forkJoin(tasks$).subscribe(results => { 
        console.log(results);

        this.source.refresh();
        this.loading = false;

        this.toastrService.success('Success', 'Eventi eliminati');
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

  eventSelectRows(selectedList) {
    this.selectedEvents = selectedList;
    console.log('Selected events', this.selectedEvents);
  }

  requestExport(): void {
    console.log('Request export');

    this.accountsService.getAccount('me', )
      .subscribe((user: AccountDTO) => {
        console.log('Fetched user', user)

        this.authService.getToken().subscribe((token: NbAuthSimpleToken) => {
          let exp = {} as ExportDTO;
          exp.sendTo = [user.email];
          exp.reqHeaders = {
            'Authorization': 'Bearer '+token.getValue()
          };
          exp.url = 'http://localhost:8080/v1/events';
          this.exportsService.createEvent1(exp, '').subscribe((res: StgdrvResponseDTO) => {
            console.log('Res', res)

            this.toastrService.success('Success', 'Riceverai a breve una email con i dati');
          });
        });
      }, err => {
        console.error("Unable to retrieve user", err);
        this.router.navigate(['auth/login']);
      });
  }
}
