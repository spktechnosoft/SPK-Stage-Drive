import { Component, TemplateRef, ViewChild, ViewContainerRef } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { AdminService, CatalogsService, StgdrvResponseDTO, ExportsService, ExportDTO, AccountsService, AccountDTO } from '../../api';
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
import { CatalogEditComponent } from './catalog-edit.component';
import { UserEditComponent } from '../users/user-edit.component';
import { UsersComponent } from '../users/users.component';

@Component({
  selector: 'ngx-catalogs',
  templateUrl: './catalogs.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `],
  providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}]
})
export class CatalogsComponent {

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
  bundle: any;

  constructor(private http: HttpClient,
              private location: Location,
              private router: Router,
              private catalogsService: CatalogsService,
              private toastrService: NbToastrService,
              private sanitizer: DomSanitizer,
              private bundleService: BundleService,
              private exportsService: ExportsService,
              private accountsService: AccountsService,
              private authService: NbAuthService,
              private adminService: AdminService,
              private windowService: NbWindowService) {

    this.bundle = bundleService.bundle;
    var items = this.bundle['event-category'].items;
    
    this.source = new CustomServerDataSource(http, {
      endPoint: environment.endpoint+'/v1/catalogs?size=true',
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

    this.router.navigate(['pages/catalogs', event.data.id]).then( (e) => {
      if (e) {
        console.log("Navigation is successful!");
      } else {
        console.log("Navigation has failed!");
      }
    });
  }

  onDelete(event): void {
    // if (window.confirm('Sei sicuro di voler procedere?')) {
    //   this.catalogsService.deleteEvent(event.data.id, '').subscribe((res: StgdrvResponseDTO) => {
    //     console.log('Res', res)
    //     this.source.refresh();
    //     this.loading = false;
  
    //     this.toastrService.success('Success', res.message);
    //   });
    // } else {
    //   this.loading = false;
    // }
  }

  requestBundleUpdate(): void {
    console.log('Request bundle update');

    this.loading = true;

    this.adminService.updateBundle().subscribe((res: StgdrvResponseDTO) => {
      console.log('Res', res)
      this.loading = false;

      this.toastrService.success('Success', 'Richiesta inviata');
    }, err => {
      console.error("Fail", err);
      this.toastrService.danger('Error', err.error.message);
      this.loading = false;
    });
  }
}
