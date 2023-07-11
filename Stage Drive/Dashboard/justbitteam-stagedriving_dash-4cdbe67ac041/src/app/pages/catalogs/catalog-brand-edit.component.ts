import { Component, Input, ElementRef, ViewChild } from '@angular/core';
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { CatalogsService, BrandsService, CatalogDTO, CoordinateDTO, StgdrvResponseDTO, ObjectsService, ObjectDTO, ItemDTO, BrandDTO } from '../../api';
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
  selector: 'ngx-catalog-brand-edit',
  templateUrl: './catalog-brand-edit.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `]
})
export class CatalogBrandEditComponent {

  catalogId;
  brand;
  loading = false;
  imgLoading = false;
  @Input() id: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private catalogsService: CatalogsService,
    private brandsService: BrandsService,
    private objectsService: ObjectsService,
    private toastrService: NbToastrService,
    private bundleService: BundleService
  ) {
    
  }

  ngOnInit() {
    if (this.route.snapshot.paramMap.get('brandId')) {
      this.id = this.route.snapshot.paramMap.get('brandId');
    }
    if (this.route.snapshot.paramMap.get('id')) {
      this.catalogId = this.route.snapshot.paramMap.get('id');
    }

    this.loading = true;
    this.imgLoading = false;

    if (this.id != 'new') {
      this.brandsService.getBrand(this.id, '')
        .subscribe((brand: BrandDTO) => {
          console.log('Brand', brand)

          this.brand = brand;
          
          this.loading = false;
        });
      } else {
        console.log('New brand');

        this.loading = false;
        this.brand = {
          
        } as BrandDTO;
      }
  }

  save() {
    console.log('Saving');

    this.loading = true;

    if (this.id != 'new') { 
      this.brandsService.modifyBrand(this.brand, this.id, '').subscribe((res: StgdrvResponseDTO) => {
        console.log('Res', res)
        this.loading = false;

        this.toastrService.success('Success', res.message);
      }, err => {
        console.error("Fail", err);
        this.toastrService.danger('Error', err.error.message);
        this.loading = false;
      });
    } else {
      this.brandsService.createBrand(this.brand, '').subscribe((res: StgdrvResponseDTO) => {
        console.log('Res', res)
        this.loading = false;

        this.toastrService.success('Success', res.message);

        this.router.navigate(['pages/catalogs', this.catalogId, 'brands', res.id]);
      }, err => {
        console.error("Fail", err);
        this.toastrService.danger('Error', err.error.message);
        this.loading = false;
      });
    }
  }


}
