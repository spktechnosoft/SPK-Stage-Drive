import { NgModule } from '@angular/core';
import { ThemeModule } from '../../@theme/theme.module';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { SearchComponent } from './search-map/search/search.component';
import { SearchMapComponent } from './search-map/search-map.component';
import { BundleService } from './bundle.service';
import { NbDatepickerModule } from '@nebular/theme';
import { CKEditorModule } from 'ng2-ckeditor';

@NgModule({
  imports: [
    ThemeModule,
    Ng2SmartTableModule,
    NbDatepickerModule,
    CKEditorModule
  ],
  entryComponents: [
    
  ],
  declarations: [
    SearchComponent,
    SearchMapComponent
  ],
  providers: [
    
  ],
  exports: [
    SearchComponent
  ]
})
export class CommonsModule { }
