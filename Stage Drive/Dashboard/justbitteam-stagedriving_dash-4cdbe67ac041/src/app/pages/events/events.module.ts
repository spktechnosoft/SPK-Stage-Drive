import { NgModule } from '@angular/core';
import { ThemeModule } from '../../@theme/theme.module';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { CommonsModule } from '../commons/commons.module';
import { EventsComponent } from './events.component';
import { EventEditComponent } from './event-edit.component';
import { UserRenderComponent } from '../users/user-render.component';
import { UserEditComponent } from '../users/user-edit.component';
import { UsersComponent } from '../users/users.component';
import { SearchComponent } from '../commons/search-map/search/search.component';
import { EventRenderComponent } from '../events/event-render.component';
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
    EventsComponent,
    EventEditComponent,
    EventRenderComponent
  ],
  exports: [
    EventRenderComponent,
    EventEditComponent
  ],
  entryComponents: [
    UserRenderComponent,
    UserEditComponent,
    EventEditComponent,
    UsersComponent
  ],
})
export class EventsModule { }
