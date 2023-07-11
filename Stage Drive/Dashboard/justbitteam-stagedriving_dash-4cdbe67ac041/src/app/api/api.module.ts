import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Configuration } from './configuration';

import { AccountsService } from './api/accounts.service';
import { ActionsService } from './api/actions.service';
import { AuthService } from './api/auth.service';
import { BillingsService } from './api/billings.service';
import { BookingsService } from './api/bookings.service';
import { BrandsService } from './api/brands.service';
import { BundleService } from './api/bundle.service';
import { CatalogsService } from './api/catalogs.service';
import { CheckinsService } from './api/checkins.service';
import { ColorsService } from './api/colors.service';
import { CommentsService } from './api/comments.service';
import { ConnectionsService } from './api/connections.service';
import { DevicesService } from './api/devices.service';
import { EventsService } from './api/events.service';
import { FamiliesService } from './api/families.service';
import { FeedsService } from './api/feeds.service';
import { FellowshipsService } from './api/fellowships.service';
import { GroupsService } from './api/groups.service';
import { InterestsService } from './api/interests.service';
import { ItemsService } from './api/items.service';
import { LikesService } from './api/likes.service';
import { ObjectsService } from './api/objects.service';
import { PaymentsService } from './api/payments.service';
import { ReviewsService } from './api/reviews.service';
import { RidesService } from './api/rides.service';
import { TransactionsService } from './api/transactions.service';
import { ExportsService } from './api/exports.service';
import { AdminJobsService } from './api/adminJobs.service';
import { AdminNodesService } from './api/adminNodes.service';
import { AdminQueuesService } from './api/adminQueues.service';
import { AdminService } from './api/admin.service';


@NgModule({
  imports:      [ CommonModule, HttpClientModule ],
  declarations: [],
  exports:      [],
  providers: [
    AccountsService,
    ActionsService,
    AuthService,
    BillingsService,
    BookingsService,
    BrandsService,
    BundleService,
    CatalogsService,
    CheckinsService,
    ColorsService,
    CommentsService,
    ConnectionsService,
    DevicesService,
    EventsService,
    FamiliesService,
    FeedsService,
    FellowshipsService,
    GroupsService,
    InterestsService,
    ItemsService,
    LikesService,
    ObjectsService,
    PaymentsService,
    ReviewsService,
    RidesService,
    TransactionsService,
    ExportsService,
    AdminJobsService, 
    AdminQueuesService,
    AdminNodesService,
    AdminService
]
})
export class ApiModule {
    public static forRoot(configurationFactory: () => Configuration): ModuleWithProviders {
        return {
            ngModule: ApiModule,
            providers: [ { provide: Configuration, useFactory: configurationFactory } ]
        }
    }

    constructor( @Optional() @SkipSelf() parentModule: ApiModule) {
        if (parentModule) {
            throw new Error('ApiModule is already loaded. Import your base AppModule only.');
        }
    }
}
