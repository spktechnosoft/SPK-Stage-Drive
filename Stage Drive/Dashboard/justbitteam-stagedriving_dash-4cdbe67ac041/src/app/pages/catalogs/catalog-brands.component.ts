import { Component, Input, TemplateRef, ViewChild, ViewContainerRef } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { CatalogDTO, CatalogsService, StgdrvResponseDTO, ExportsService, ExportDTO, AccountsService, AccountDTO } from '../../api';
import { CustomServerDataSource } from '../server.data-source';
import { HttpClient, HttpHeaders, HttpParams,
  HttpResponse, HttpEvent } from '@angular/common/http';
import { Location, LocationStrategy, PathLocationStrategy, DatePipe } from '@angular/common';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { UserRenderComponent } from "../users/user-render.component";
import { environment } from '../../../environments/environment';
import { NbToastrService, NbWindowService } from '@nebular/theme';
import { BundleService } from '../commons/bundle.service';
import { NbAuthService, NbAuthSimpleToken } from '@nebular/auth';
import { CatalogEditComponent } from './catalog-edit.component';
import { UserEditComponent } from '../users/user-edit.component';
import { UsersComponent } from '../users/users.component';

@Component({
  selector: 'ngx-catalog-brands',
  templateUrl: './catalog-brands.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `],
  providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}]
})
export class CatalogBrandsComponent {


  @Input() id: string;

  loading = false;

  settings = {
    pager : {
      perPage:10,
      display: true
    },
    hideSubHeader: false, 
    actions:{add:false, edit:true, delete:false},
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
    columns: {
      name: {
        title: 'Nome',
        type: 'text',
        filter: false,
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
      modified: {
        title: 'Data aggiornamento',
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

  constructor(private http: HttpClient,
              private location: Location,
              private router: Router,
              private route: ActivatedRoute,
              private catalogsService: CatalogsService,
              private toastrService: NbToastrService,
              private sanitizer: DomSanitizer,
              private bundleService: BundleService,
              private exportsService: ExportsService,
              private accountsService: AccountsService,
              private authService: NbAuthService,
              private windowService: NbWindowService) {

    if (this.route.snapshot.paramMap.get('id')) {
      this.id = this.route.snapshot.paramMap.get('id');
    }

    this.source = new CustomServerDataSource(http, {
      endPoint: environment.endpoint+'/v1/brands?size=true',
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

    this.router.navigate(['pages/catalogs', this.id, 'brands', event.data.id]).then( (e) => {
      if (e) {
        console.log("Navigation is successful!");
      } else {
        console.log("Navigation has failed!");
      }
    });
  }

  onDelete(event): void {
  //   if (window.confirm('Sei sicuro di voler procedere?')) {
  //     this.catalogsService.deleteEvent(event.data.id, '').subscribe((res: StgdrvResponseDTO) => {
  //       console.log('Res', res)
  //       this.source.refresh();
  //       this.loading = false;
  
  //       this.toastrService.success('Success', res.message);
  //     });
  //   } else {
  //     this.loading = false;
  //   }
  }

  // requestExport(): void {
  //   console.log('Request export');

  //   this.accountsService.getAccount('me', )
  //     .subscribe((user: AccountDTO) => {
  //       console.log('Fetched user', user)

  //       this.authService.getToken().subscribe((token: NbAuthSimpleToken) => {
  //         let exp = {} as ExportDTO;
  //         exp.sendTo = [user.email];
  //         exp.reqHeaders = {
  //           'Authorization': 'Bearer '+token.getValue()
  //         };
  //         exp.url = 'http://localhost:8080/v1/events';
  //         this.exportsService.createEvent1(exp, '').subscribe((res: StgdrvResponseDTO) => {
  //           console.log('Res', res)

  //           this.toastrService.success('Success', 'Riceverai a breve una email con i dati');
  //         });
  //       });
  //     }, err => {
  //       console.error("Unable to retrieve user", err);
  //       this.router.navigate(['auth/login']);
  //     });
  // }
}
