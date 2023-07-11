import { Component, Input, ElementRef, ViewChild } from '@angular/core';
import {FormGroup, FormControl, ReactiveFormsModule} from '@angular/forms';
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { CatalogsService, CatalogDTO, BrandsService, BrandDTO, CoordinateDTO, StgdrvResponseDTO, ObjectsService, ObjectDTO, ItemDTO } from '../../api';
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
  selector: 'ngx-catalog-item-edit',
  templateUrl: './catalog-item-edit.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `]
})
export class CatalogItemEditComponent {

  catalogId;
  item;
  loading = false;
  imgLoading = false;
  brand;
  brandModel;
  brands;
  showBrandSelector = false;
  @Input() id: string;

  @ViewChild('brandinput')
  public brandElementRef: ElementRef;

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
    if (this.route.snapshot.paramMap.get('id')) {
      this.catalogId = this.route.snapshot.paramMap.get('id');
    }
    if (this.route.snapshot.paramMap.get('itemId')) {
      this.id = this.route.snapshot.paramMap.get('itemId');
    }

    this.loading = true;
    this.imgLoading = false;

    if (this.id != 'new') {
      this.catalogsService.getItemOfCatalog(this.catalogId, this.id, '')
        .subscribe((item: ItemDTO) => {
          console.log('Item', item)

          this.item = item;
          
          this.loading = false;
        });
      } else {
        console.log('New item');

        this.loading = false;
        this.item = {
          
        } as ItemDTO;
      }
  }

  brandChanged() {
    console.log('changed', this.brand);
    this.brandsService.getBrands('', this.brand, true, '20', '0').subscribe((brands: BrandDTO[]) => {
      console.log('Brands', brands)
      
      this.brands = brands;
      
    });
  }

  chooseBrand(b: any) {
    this.item.brands = [
      b
    ];

    this.brands = null;
    this.showBrandSelector = false;
  }

  save() {
    console.log('Saving');

    this.loading = true;

    if (this.id != 'new') { 
      this.catalogsService.modifyItemOfCalaog(this.catalogId, this.id, this.item, '').subscribe((res: StgdrvResponseDTO) => {
        console.log('Res', res)
        this.loading = false;

        this.toastrService.success('Success', res.message);
      }, err => {
        console.error("Fail", err);
        this.toastrService.danger('Error', err.error.message);
        this.loading = false;
      });
    } else {
      this.catalogsService.addItemToCalaog(this.catalogId, this.item, '').subscribe((res: StgdrvResponseDTO) => {
        console.log('Res', res)
        this.loading = false;

        this.toastrService.success('Success', res.message);

        this.router.navigate(['pages/catalogs', this.catalogId, 'items', res.id]);
      }, err => {
        console.error("Fail", err);
        this.toastrService.danger('Error', err.error.message);
        this.loading = false;
      });
    }
  }


}
