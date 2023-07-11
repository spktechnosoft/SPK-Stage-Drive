import { Component } from '@angular/core';
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { AccountsService, AccountDTO, ExportsService, ExportDTO, StgdrvResponseDTO } from '../../api';
import { CustomServerDataSource } from '../server.data-source';
import { HttpClient, HttpHeaders, HttpParams,
  HttpResponse, HttpEvent }                           from '@angular/common/http';
import { Location, LocationStrategy, PathLocationStrategy, DatePipe } from '@angular/common';
import { Router } from "@angular/router";
import { environment } from '../../../environments/environment';
import { BundleService } from '../commons/bundle.service';
import { NbToastrService } from '@nebular/theme';
import { NbAuthService, NbAuthSimpleToken } from '@nebular/auth';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'ngx-users',
  templateUrl: './users.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `],
  providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}]
})
export class UsersComponent {

  loading = false;
  roles = {
    'user': 'Utente',
    'driver': 'Autista',
    'organizer': 'Organizzatore',
    'admin': 'Amministratore'
  };
  pageSizes = [
    '10', '20'
  ]
  pageSize = '20';

  categories: any[] = [];
  categoriesKeys: any[] = [];
  styles: any[] = [];
  stylesKeys: any[] = [];

  selectedUsers: any[];

  settings = {
    selectMode: 'multi',
    pager : {
      perPage: this.pageSize,
      display: true
    },                            //pagination – rows per page
    hideSubHeader: false,                             //hide header searchboxes for search
    actions:{add:false, edit:true, delete:true},   //hide first column having ADD DELETE anchors
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
    columns: {
      id: {
        title: 'ID',
        type: 'text',
      },
      firstname: {
        title: 'First Name',
        type: 'text',
      },
      lastname: {
        title: 'Last Name',
        type: 'text',
      },
      gender: {
        title: 'Gender',
        type: 'text',
        filter: {
          type: 'list',
          config: {
            selectText: 'Scegli',
            list: [
              { value: '', title: '-' },
              { value: 'male', title: 'Uomo' },
              { value: 'female', title: 'Donna' },
            ],
          },
        },
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            return value == 'male' ? 'Uomo' : 'Donna';
          }
        }
      },
      email: {
        title: 'E-mail',
        type: 'text',
      },
      telephone: {
        title: 'Phone',
        type: 'text',
      },
      favCategory: {
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
      favStyle: {
        title: 'Stile',
        type: 'text',
        filter: {
          type: 'list',
          config: {
            selectText: 'Scegli',
            list: this.styles,
          },
        },
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            let index = this.stylesKeys.indexOf(value);
            if (index !== -1) {
              return this.styles[index].title;
            } else {
              return value;
            }
          }
        }
      },
      rating: {
        title: 'Rating',
        type: 'text',
      },
      groups: {
        title: 'Ruoli',
        filter: {
          type: 'list',
          config: {
            selectText: 'Scegli',
            list: [
              { value: 'user', title: 'Utente' },
              { value: 'driver', title: 'Autista' },
              { value: 'organizer', title: 'Organizzatore' },
              { value: 'admin', title: 'Amministratore' }
            ],
          },
        },
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            let groups = '';
            value.forEach(element => {
              groups += this.roles[element.name] + ', ';
            });
            return groups.substring(0, groups.length-2);
          }
        }
      },
      vehicles: {
        title: 'Veicolo',
        filter: false,
        sort: false,
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            let vehicles = '';
            value.forEach(element => {
              vehicles += element.manufacturer + ' - ' +element.name + ' (' + element.features + ')';
            });
            return vehicles;
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

  constructor(private accountsService: AccountsService, 
              private http: HttpClient,
              private location: Location,
              private router: Router,
              private bundleService: BundleService,
              private exportsService: ExportsService,
              public authService: NbAuthService,
              private toastrService: NbToastrService,) {

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

    var items = this.bundle['style'].items;
    items.forEach(item => {
      console.log('item', item);
      this.styles.push({
        'value': item.name,
        'title': item.description
      });
      this.stylesKeys.push(item.name);
    });

    this.source = new CustomServerDataSource(http, {
      endPoint: environment.endpoint+'/v1/accounts?size=true',
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

    window.open("/#/pages/users/"+event.data.id, "_blank");
    return;
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
      this.loading = true;
      this.accountsService.deleteAccount(event.data.id).subscribe((res: StgdrvResponseDTO) => {
        console.log('Res', res)
        this.source.refresh();
        this.loading = false;
  
        this.toastrService.success('Success', res.message);
      }, err => {
        console.error("Unable to delete user", err);
        this.toastrService.danger('['+err.error.code+'] '+err.error.message, 'Impossibile eliminare l\'utente');
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
      this.selectedUsers.forEach(user => {
        tasks$.push(this.accountsService.deleteAccount(user.id));
      });

      forkJoin(tasks$).subscribe(results => { 
        console.log(results);

        this.source.refresh();
        this.loading = false;

        this.toastrService.success('Success', 'Utenti eliminati');
      }, err => {
        console.error("Unable to delete user", err);
        this.toastrService.danger('['+err.error.code+'] '+err.error.message, 'Impossibile eliminare gli utenti');
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

  userSelectRows(selectedList) {
    this.selectedUsers = selectedList;
    console.log('Selected users', this.selectedUsers);
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
          exp.url = 'http://localhost:8080/v1/accounts';
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
