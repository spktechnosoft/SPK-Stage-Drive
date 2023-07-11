import { Component, Input, ElementRef, ViewChild } from '@angular/core';
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { CatalogsService, CatalogDTO, CoordinateDTO, StgdrvResponseDTO, ObjectsService, ObjectDTO } from '../../api';
import { CustomServerDataSource } from '../server.data-source';
import { HttpClient, HttpHeaders, HttpParams,
  HttpResponse, HttpEvent }                           from '@angular/common/http';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { NbToastrService } from '@nebular/theme';
import { Location } from '../commons/search-map/entity/Location';
import { SearchComponent } from '../commons/search-map/search/search.component';
import { SearchMapComponent } from '../commons/search-map/search-map.component';
import '../commons/ckeditor/ckeditor.loader';
import 'ckeditor';
import * as moment from 'moment';
import { ImageCroppedEvent } from 'ngx-image-cropper';
import { BundleService } from '../commons/bundle.service';
import { NbWindowRef } from '@nebular/theme';


@Component({
  selector: 'ngx-catalog-edit',
  templateUrl: './catalog-edit.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `]
})
export class CatalogEditComponent {

  catalog;
  showCatalogForm;
  loading = false;
  imgLoading = false;
  @Input() id: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private catalogsService: CatalogsService,
    private objectsService: ObjectsService,
    private toastrService: NbToastrService,
    private bundleService: BundleService
  ) {
    
  }

  ngOnInit() {
    if (this.route.snapshot.paramMap.get('id')) {
      this.id = this.route.snapshot.paramMap.get('id');
    }

    this.loading = true;
    this.imgLoading = false;

    if (this.id != 'new') {
      this.catalogsService.getCatalog(this.id, '')
        .subscribe((catalog: CatalogDTO) => {
          console.log('Catalog', catalog)

          this.catalog = catalog;
          
          this.loading = false;
        });
      } else {
        this.showCatalogForm = true;
        this.loading = false;
        this.catalog = {
          
        } as CatalogDTO;
      }
  }

  save() {
    console.log('Saving');

    this.loading = true;

    if (this.id != 'new') { 
      this.catalogsService.modifyCatalog(this.catalog, this.catalog.id).subscribe((res: StgdrvResponseDTO) => {
        console.log('Res', res)
        this.loading = false;

        this.toastrService.success('Success', res.message);
      }, err => {
        console.error("Fail", err);
        this.toastrService.danger('Error', err.error.message);
        this.loading = false;
      });
    } else {
      this.catalogsService.createCatalog(this.catalog, '').subscribe((res: StgdrvResponseDTO) => {
        console.log('Res', res)
        this.loading = false;

        this.toastrService.success('Success', res.message);

        this.router.navigate(['pages/catalogs', res.id]);
        this.showCatalogForm = false;
        this.id = res.id;
      }, err => {
        console.error("Fail", err);
        this.toastrService.danger('Error', err.error.message);
        this.loading = false;
      });
    }
  }


}
