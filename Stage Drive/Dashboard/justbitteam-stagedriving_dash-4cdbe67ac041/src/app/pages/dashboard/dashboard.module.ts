import { NgModule } from '@angular/core';
import { NbDatepickerModule } from '@nebular/theme';
import { ThemeModule } from '../../@theme/theme.module';
import { DashboardComponent, SafePipe } from './dashboard.component';

@NgModule({
  imports: [
    ThemeModule,
    NbDatepickerModule,
  ],
  declarations: [
    DashboardComponent,
    SafePipe
  ],
})
export class DashboardModule { }
