import { Component } from '@angular/core';
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { RidesService, RideDTO, CoordinateDTO, StgdrvResponseDTO, ObjectsService, ObjectDTO } from '../../api';
import { CustomServerDataSource } from '../server.data-source';
import { HttpClient, HttpHeaders, HttpParams,
  HttpResponse, HttpEvent }                           from '@angular/common/http';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { NbToastrService } from '@nebular/theme';
import { Location } from '../commons/search-map/entity/Location';
import { SearchComponent } from '../commons/search-map/search/search.component';
import { SearchMapComponent } from '../commons/search-map/search-map.component';
import '../commons/ckeditor/ckeditor.loader';
import 'ckeditor';

@Component({
  selector: 'ngx-ride-edit',
  templateUrl: './ride-edit.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `]
})
export class RideEditComponent {

  ride;
  loading = false;
  imgLoading = false;
  searchedLocation: Location;
  public imagePath;
  imgURL: any;
  public message: string;
  addLocation = false;

  items = [
    {
      id: 'name',
      label: 'Nome',
      type: 'text'
    },
    {
      id: 'name',
      label: 'Nome',
      type: 'text'
    }
  ]

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ridesService: RidesService,
    private objectsService: ObjectsService,
    private toastrService: NbToastrService
  ) {
    
  }

  ngOnInit() {
    let id = this.route.snapshot.paramMap.get('id');

    this.loading = true;
    this.imgLoading = false;
    this.ridesService.getRide(id)
      .subscribe((ride: RideDTO) => {
        console.log('Ride', ride)
        this.ride = ride

        if (this.ride.images && this.ride.images[0].uri) {
          this.imgURL = this.ride.images[0].uri;
        }
        this.loading = false;
      });
  }

  save() {
    console.log('Saving');

    this.loading = true;
    this.ridesService.editRide(this.ride, this.ride.id).subscribe((res: StgdrvResponseDTO) => {
      console.log('Res', res)
      this.loading = false;

      this.toastrService.success('Success', res.message);
    });
  }

  updateLocation(event: Location) {
    console.log('New location', event);
    this.searchedLocation = new Location(event.latitude, event.longitude, event.address, event.country, event.city, event.zipcode, event.town);

    this.ride.address = event.address;
    this.ride.coordinate = {
      latitude: event.latitude,
      longitude: event.longitude
    } as CoordinateDTO;
  }
}
