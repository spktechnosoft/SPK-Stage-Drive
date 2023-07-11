import { NgModule } from '@angular/core';


import { ThemeModule } from '../../@theme/theme.module';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { UsersComponent } from './users.component';
import { UserEditComponent } from './user-edit.component';
import { UserRenderComponent } from './user-render.component';

@NgModule({
  imports: [
    ThemeModule,
    Ng2SmartTableModule,
  ],
  declarations: [
    UsersComponent,
    UserEditComponent,
    UserRenderComponent
  ],
  exports: [
    UserRenderComponent,
    UserEditComponent,
    UsersComponent
  ],
  entryComponents: [
    UserRenderComponent,
    UserEditComponent,
    UsersComponent
  ],
})
export class UsersModule { }
