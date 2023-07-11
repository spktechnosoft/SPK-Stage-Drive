import { NgModule } from '@angular/core';

import { PagesComponent } from './pages.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { UsersModule } from './users/users.module';
import { TransactionsModule } from './transactions/transactions.module';
import { EventsModule } from './events/events.module';
import { RidesModule } from './rides/rides.module';
import { CatalogsModule } from './catalogs/catalogs.module';
import { PagesRoutingModule } from './pages-routing.module';
import { ThemeModule } from '../@theme/theme.module';
import { MiscellaneousModule } from './miscellaneous/miscellaneous.module';
import { CommonsModule } from './commons/commons.module';
import { AdminModule } from './admin/admin.module';
import { BundleResolver } from './bundle.resolver';
import { UserEditComponent } from './users/user-edit.component';

const PAGES_COMPONENTS = [
  PagesComponent,
];

@NgModule({
  imports: [
    PagesRoutingModule,
    ThemeModule,
    DashboardModule,
    UsersModule,
    MiscellaneousModule,
    TransactionsModule,
    EventsModule,
    RidesModule,
    CatalogsModule,
    CommonsModule,
    AdminModule
  ],
  declarations: [
    ...PAGES_COMPONENTS,
  ],
  providers: [
    BundleResolver
  ]
})
export class PagesModule {
}
