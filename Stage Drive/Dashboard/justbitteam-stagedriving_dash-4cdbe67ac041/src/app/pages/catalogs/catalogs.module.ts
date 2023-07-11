import { NgModule } from '@angular/core';
import { ThemeModule } from '../../@theme/theme.module';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { CommonsModule } from '../commons/commons.module';
import { CatalogsComponent } from './catalogs.component';
import { CatalogEditComponent } from './catalog-edit.component';
import { CatalogItemsComponent } from './catalog-items.component';
import { CatalogItemEditComponent } from './catalog-item-edit.component';
import { CatalogBrandEditComponent } from './catalog-brand-edit.component';
import { CatalogBrandsComponent } from './catalog-brands.component';
import { UserRenderComponent } from '../users/user-render.component';
import { UserEditComponent } from '../users/user-edit.component';
import { UsersComponent } from '../users/users.component';
import { SearchComponent } from '../commons/search-map/search/search.component';
import { CatalogRenderComponent } from '../catalogs/catalog-render.component';
import { CatalogBrandRenderComponent } from '../catalogs/catalog-brand-render.component';
import { NbDatepickerModule } from '@nebular/theme';
import { CKEditorModule } from 'ng2-ckeditor';
import { ImageCropperModule } from 'ngx-image-cropper';

@NgModule({
  imports: [
    ThemeModule,
    Ng2SmartTableModule,
    NbDatepickerModule,
    CommonsModule,
    CKEditorModule,
    ImageCropperModule
  ],
  declarations: [
    CatalogsComponent,
    CatalogEditComponent,
    CatalogRenderComponent,
    CatalogBrandRenderComponent,
    CatalogItemsComponent,
    CatalogItemEditComponent,
    CatalogBrandsComponent,
    CatalogBrandEditComponent
  ],
  exports: [
    CatalogRenderComponent,
    CatalogBrandRenderComponent,
    CatalogEditComponent,
    CatalogItemsComponent,
    CatalogItemEditComponent,
    CatalogBrandsComponent,
    CatalogBrandEditComponent
  ],
  entryComponents: [
    UserRenderComponent,
    UserEditComponent,
    CatalogEditComponent,
    CatalogItemsComponent,
    CatalogItemEditComponent,
    CatalogBrandsComponent,
    CatalogBrandEditComponent,
    CatalogBrandRenderComponent,
    UsersComponent
  ],
})
export class CatalogsModule { }
