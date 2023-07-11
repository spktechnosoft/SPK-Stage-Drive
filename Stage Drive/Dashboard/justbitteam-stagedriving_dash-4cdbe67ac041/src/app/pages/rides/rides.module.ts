import { NgModule } from '@angular/core';
import { ThemeModule } from '../../@theme/theme.module';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { RidesComponent } from './rides.component';
import { RideEditComponent } from './ride-edit.component';
import { RidePassengersComponent } from './ride-passengers.component';
import { UserRenderComponent } from '../users/user-render.component';
import { EventRenderComponent } from '../events/event-render.component';
import { NbDatepickerModule } from '@nebular/theme';
import { CommonsModule } from '../commons/commons.module';

@NgModule({
  imports: [
    ThemeModule,
    Ng2SmartTableModule,
    NbDatepickerModule,
    CommonsModule
  ],
  entryComponents: [
    UserRenderComponent,
    EventRenderComponent
  ],
  declarations: [
    RidesComponent,
    RideEditComponent,
    RidePassengersComponent
  ],
  exports: [
    
  ]
})
export class RidesModule { }
