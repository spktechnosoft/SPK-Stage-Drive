import { NgModule } from '@angular/core';


import { ThemeModule } from '../../@theme/theme.module';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { TransactionsComponent } from './transactions.component';
import { UserRenderComponent } from '../users/user-render.component';

@NgModule({
  imports: [
    ThemeModule,
    Ng2SmartTableModule,
  ],
  entryComponents: [
    UserRenderComponent
  ],
  declarations: [
    TransactionsComponent
  ],
})
export class TransactionsModule { }
