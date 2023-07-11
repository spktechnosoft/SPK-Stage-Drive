import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { RideDTO, RidesService, StgdrvResponseDTO } from '../../api';
import { CustomServerDataSource } from '../server.data-source';
import { HttpClient, HttpHeaders, HttpParams,
  HttpResponse, HttpEvent } from '@angular/common/http';
import { Location, LocationStrategy, PathLocationStrategy, DatePipe } from '@angular/common';
import { Router, ActivatedRoute } from "@angular/router";
import { UserRenderComponent } from "../users/user-render.component";
import { EventRenderComponent } from '../events/event-render.component';
import { environment } from '../../../environments/environment';
import { NbToastrService } from '@nebular/theme';

@Component({
  selector: 'ngx-ride-passengers',
  templateUrl: './ride-passengers.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `],
  providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}]
})
export class RidePassengersComponent {

  loading = false;

  settings = {
    pager : {
      perPage:10,
      display: true
    },
    hideSubHeader: false, 
    actions:{add:false, edit:false, delete:true},
    mode: 'external',
    //attr:{class:"table table-hover table-striped"},  //use bootstrap zebra style
    add: {
      addButtonContent: '<i class="nb-plus"></i>',
      createButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    edit: {
      editButtonContent: '<i class="nb-edit"></i>',
      saveButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    delete: {
      deleteButtonContent: '<i class="nb-trash"></i>',
      confirmDelete: true,
    },
    /**
     * 
      accountFrom: {id: "15426500890499602162", created: "2018-11-30T16:53:12.000Z", modified: "2018-11-30T16:53:12.000Z",…}
      accountTo: {id: "15405801789888601659", created: "2018-11-06T17:54:42.000Z", modified: "2018-11-06T17:54:42.000Z",…}
      amount: 10
      created: "2019-06-13T09:37:52.000Z"
      event: {id: "15408180286222664037", created: "2018-11-09T11:58:51.000Z", modified: "2018-11-09T11:58:51.000Z",…}
      fee: 10.72
      id: "15594719693986905484"
      modified: "2019-06-13T09:37:52.000Z"
      payedAt: "2019-06-13T09:37:54.000Z"
      provider: "paypal"
      providerToken: "fake-valid-nonce"
      refundedAt: "2019-06-13T09:40:25.000Z"
      ride: {id: "15576576314325590037", created: "2019-05-23T09:38:54.000Z", modified: "2019-05-23T09:38:54.000Z",…}
      status: "deleted"
      totalAmount: 20.72
     */
    columns: {
      id: {
        title: 'ID',
        type: 'text',
      },
      account: {
        title: 'Passeggero',
        type: 'custom',
        filter: false,
        sort: false,
        renderComponent: UserRenderComponent
      },
      // status: {
      //   title: 'Stato',
      //   type: 'text',
      //   filter: {
      //     type: 'list',
      //     config: {
      //       selectText: 'Scegli',
      //       list: [
      //         { value: 'draft', title: 'Bozza' },
      //         { value: 'published', title: 'Pubblicato' }
      //       ],
      //     },
      //   },
      //   valuePrepareFunction: (value) => {
      //     if (value.length == 0)
      //       return '-';
      //     else {
      //       return this.statuses[value];
      //     }
      //   }
      // },
      seats: {
        title: 'Posti',
        type: 'text',
        filter: false,
      },
      // totalPrice: {
      //   title: 'Prezzo totale',
      //   type: 'text',
      //   filter: false,
      //   valuePrepareFunction: (value) => {
      //     if (value.length == 0)
      //       return '-';
      //     else {
      //       return value+' €';
      //     }
      //   }
      // },
      // price: {
      //   title: 'Prezzo',
      //   type: 'text',
      //   filter: false,
      //   valuePrepareFunction: (value) => {
      //     if (value.length == 0)
      //       return '-';
      //     else {
      //       return value.price+' + '+value.fee+' €';
      //     }
      //   }
      // },
      // fromEvent: {
      //   title: 'Evento di partenza',
      //   type: 'custom',
      //   filter: false,
      //   sort: false,
      //   renderComponent: EventRenderComponent,
      // },
      // fromEventId: {
      //   title: 'ID Evento di partenza',
      //   type: 'text',
      //   filter: true
      // },
      // toEvent: {
      //   title: 'Evento di destinazione',
      //   type: 'custom',
      //   filter: false,
      //   sort: false,
      //   renderComponent: EventRenderComponent,
      // },
      // toEventId: {
      //   title: 'ID Evento di destinazione',
      //   type: 'text',
      //   filter: true
      // },
      // category: {
      //   title: 'Categoria',
      //   type: 'text',
      //   filter: {
      //     type: 'list',
      //     config: {
      //       selectText: 'Scegli',
      //       list: [
      //         { value: 'Festival', title: 'Festival' },
      //       ],
      //     },
      //   },
      //   valuePrepareFunction: (value) => {
      //     if (value.length == 0)
      //       return '-';
      //     else {
      //       return this.categories[value];
      //     }
      //   }
      // },
      // goingDepartureDate: {
      //   title: 'Data partenza',
      //   type: 'text',
      //   filter: false,
      //   valuePrepareFunction: (value) => {
      //     if (value.length == 0)
      //       return '-';
      //     else {
      //       let pipe = new DatePipe('it-IT'); // Use your own locale
      //       return pipe.transform(value, 'short');
      //     }
      //   }
      // },
      // goingArrivalDate: {
      //   title: 'Data arrivo',
      //   type: 'text',
      //   filter: false,
      //   valuePrepareFunction: (value) => {
      //     if (value.length == 0)
      //       return '-';
      //     else {
      //       let pipe = new DatePipe('it-IT'); // Use your own locale
      //       return pipe.transform(value, 'short');
      //     }
      //   }
      // },
      // returnDepartureDate: {
      //   title: 'Data ritorno',
      //   type: 'text',
      //   filter: false,
      //   valuePrepareFunction: (value) => {
      //     if (value.length == 0)
      //       return '-';
      //     else {
      //       let pipe = new DatePipe('it-IT'); // Use your own locale
      //       return pipe.transform(value, 'short');
      //     }
      //   }
      // },
      created: {
        title: 'Data creazione',
        type: 'text',
        filter: false,
        valuePrepareFunction: (value) => {
          if (value.length == 0)
            return '-';
          else {
            let pipe = new DatePipe('it-IT'); // Use your own locale
            return pipe.transform(value, 'short');
          }
        }
      },
    },
  };

  source: ServerDataSource;
  rideId: any;

  constructor(private http: HttpClient,
              private location: Location,
              private router: Router,
              private route: ActivatedRoute,
              private ridesService: RidesService,
              private toastrService: NbToastrService,
              private sanitizer: DomSanitizer) {
    let id = this.route.snapshot.paramMap.get('id');
    this.rideId = id;

    this.source = new CustomServerDataSource(http, {
      endPoint: environment.endpoint+'/v1/rides/'+id+'/passengers?size=true',
      pagerLimitKey:"limit",
      pagerPageKey:"page",
      sortDirKey: "order",
      sortFieldKey: "sort",
      totalKey: 'size',
      dataKey: 'data'
    });
  }

  // onEdit(event): void {
  //   console.log('ride', event);
  //   //this.location.go('/users/'+event.data.id);
  //   this.router.navigate(['pages/rides', event.data.id]).then( (e) => {
  //     if (e) {
  //       console.log("Navigation is successful!");
  //     } else {
  //       console.log("Navigation has failed!");
  //     }
  //   });
  // }

  onDelete(event): void {
    if (window.confirm('Sei sicuro di voler procedere?')) {
      this.ridesService.deletePassengerToRide(event.data.id, this.rideId).subscribe((res: StgdrvResponseDTO) => {
        console.log('Res', res)
        this.source.refresh();
        this.loading = false;
  
        this.toastrService.success('Success', res.message);
      }, err => {
        this.toastrService.danger('Error', err.error.message);
        this.loading = false;
      });
    } else {
      this.loading = false;
    }
  }
}
