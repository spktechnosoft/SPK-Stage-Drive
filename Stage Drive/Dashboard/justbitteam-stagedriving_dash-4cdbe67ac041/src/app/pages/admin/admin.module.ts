import { NgModule } from '@angular/core';
import { NbDatepickerModule, NbBadgeModule } from '@nebular/theme';
import { ThemeModule } from '../../@theme/theme.module';
import { AdminComponent } from './admin.component';
import { AdminJobComponent } from './admin-job.component';
import { Ng2SmartTableModule } from 'ng2-smart-table';

@NgModule({
  imports: [
    ThemeModule,
    NbBadgeModule,
    NbDatepickerModule,
    Ng2SmartTableModule,
  ],
  declarations: [
    AdminComponent,
    AdminJobComponent,
  ],
})
export class AdminModule { }
