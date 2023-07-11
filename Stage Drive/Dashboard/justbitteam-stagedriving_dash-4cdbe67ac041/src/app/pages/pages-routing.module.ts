import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { AuthGuard } from '../auth-guard.service';
import { PagesComponent } from './pages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EventsComponent } from './events/events.component';
import { EventEditComponent } from './events/event-edit.component';
import { RidesComponent } from './rides/rides.component';
import { RideEditComponent } from './rides/ride-edit.component';
import { UsersComponent } from './users/users.component';
import { UserEditComponent } from './users/user-edit.component';
import { CatalogsComponent } from './catalogs/catalogs.component';
import { CatalogEditComponent } from './catalogs/catalog-edit.component';
import { CatalogItemEditComponent } from './catalogs/catalog-item-edit.component';
import { CatalogBrandEditComponent } from './catalogs/catalog-brand-edit.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { AdminComponent } from './admin/admin.component';
import { BundleResolver } from './bundle.resolver';

const routes: Routes = [{
  path: '',
  component: PagesComponent,
  canActivate: [AuthGuard],
  children: [
    {
      path: 'dashboard',
      component: DashboardComponent,
      resolve: { bundle: BundleResolver }
    },
    {
      path: 'admin',
      component: AdminComponent,
      resolve: { bundle: BundleResolver }
    },
    {
      path: 'events',
      component: EventsComponent,
      resolve: { bundle: BundleResolver }
    },
    {
      path: 'events/:id',
      component: EventEditComponent,
      resolve: { bundle: BundleResolver }
    },
    {
      path: 'rides',
      component: RidesComponent,
      resolve: { bundle: BundleResolver }
    },
    {
      path: 'rides/:id',
      component: RideEditComponent,
      resolve: { bundle: BundleResolver }
    },
    {
      path: 'users',
      component: UsersComponent,
      resolve: { bundle: BundleResolver }
    },
    {
      path: 'users/:id',
      component: UserEditComponent,
      resolve: { bundle: BundleResolver }
    },
    {
      path: 'catalogs',
      component: CatalogsComponent,
      resolve: { bundle: BundleResolver }
    },
    {
      path: 'catalogs/:id',
      component: CatalogEditComponent,
      resolve: { bundle: BundleResolver }
    },
    {
      path: 'catalogs/:id/items/:itemId',
      component: CatalogItemEditComponent,
      resolve: { bundle: BundleResolver }
    },
    {
      path: 'catalogs/:id/brands/:brandId',
      component: CatalogBrandEditComponent,
      resolve: { bundle: BundleResolver }
    },
    {
      path: 'transactions',
      component: TransactionsComponent,
      resolve: { bundle: BundleResolver }
    },
    {
      path: '',
      redirectTo: 'dashboard',
      pathMatch: 'full',
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagesRoutingModule {
}
