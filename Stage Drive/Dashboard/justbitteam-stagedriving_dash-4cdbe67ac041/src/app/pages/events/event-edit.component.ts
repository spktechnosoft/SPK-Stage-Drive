import { Component, Input, ElementRef, ViewChild } from '@angular/core';
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { EventsService, EventDTO, CoordinateDTO, StgdrvResponseDTO, ObjectsService, ObjectDTO } from '../../api';
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
import * as moment from 'moment';
import { ImageCroppedEvent } from 'ngx-image-cropper';
import { BundleService } from '../commons/bundle.service';
import { NbWindowRef } from '@nebular/theme';


@Component({
  selector: 'ngx-event-edit',
  templateUrl: './event-edit.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `]
})
export class EventEditComponent {

  startDate: Date = new Date();
  startTime: string;
  finishDate: Date = new Date();
  finishTime: string;
  event;
  loading = false;
  imgLoading = false;
  searchedLocation: Location;
  public imagePath;
  imgURL: any;
  public message: string;
  addLocation = false;
  @Input() id: string;
  @ViewChild('file') file: ElementRef;
  imageChangedEvent: any = '';
  croppedImage: any = '';

  categories: any[] = [];
  categoriesKeys: any[] = [];
  bundle: any;

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
    private eventsService: EventsService,
    private objectsService: ObjectsService,
    private toastrService: NbToastrService,
    private bundleService: BundleService
  ) {
    
    this.bundle = bundleService.bundle;
    var items = this.bundle['event-category'].items;
    items.forEach(item => {
      console.log('item', item);
      this.categories.push({
        'value': item.name,
        'title': item.description
      });
      this.categoriesKeys.push(item.name);
    });
    console.log('categories', this.categories);
  }

  ngOnInit() {
    if (this.route.snapshot.paramMap.get('id')) {
      this.id = this.route.snapshot.paramMap.get('id');
    }

    this.loading = true;
    this.imgLoading = false;

    if (this.id != 'new') {
      this.eventsService.getEvent(this.id, '')
        .subscribe((event: EventDTO) => {
          console.log('Event', event)

          this.event = event;
          this.startDate = new Date(this.event.start);
          this.finishDate = new Date(this.event.finish);
          this.startTime = moment(this.startDate).format('HH:mm');
          this.finishTime = moment(this.finishDate).format('HH:mm');

          if (this.event.images && this.event.images[0].uri) {
            this.imgURL = this.event.images[0].uri;
          }
          this.loading = false;
        });
      } else {
        this.loading = false;
        this.event = {
          images: [{
            uri: ''
          }],
        } as EventDTO;
      }
  }

  save() {
    console.log('Saving');

    this.loading = true;

    this.startDate.setHours(Number(this.startTime.split(':')[0]));
    this.startDate.setMinutes(Number(this.startTime.split(':')[1]));
    this.finishDate.setHours(Number(this.finishTime.split(':')[0]));
    this.finishDate.setMinutes(Number(this.finishTime.split(':')[1]));
    this.event.start = moment(this.startDate).format();
    this.event.finish = moment(this.finishDate).format();

    if (this.id != 'new') { 
      this.eventsService.modifyEvent(this.event, this.event.id, '').subscribe((res: StgdrvResponseDTO) => {
        console.log('Res', res)
        this.loading = false;

        this.toastrService.success('Success', res.message);
      }, err => {
        console.error("Fail", err);
        this.toastrService.danger('Error', err.error.message);
        this.loading = false;
      });
    } else {
      this.eventsService.createEvent(this.event, '').subscribe((res: StgdrvResponseDTO) => {
        console.log('Res', res)
        this.loading = false;

        this.toastrService.success('Success', res.message);

        this.router.navigate(['pages/events', res.id]);
      }, err => {
        console.error("Fail", err);
        this.toastrService.danger('Error', err.error.message);
        this.loading = false;
      });
    }
  }

  updateLocation(event: Location) {
    console.log('New location', event);
    this.searchedLocation = new Location(event.latitude, event.longitude, event.address, event.country, event.city, event.zipcode, event.town);

    this.event.address = event.address;
    this.event.town = event.town;
    this.event.zipcode = event.zipcode;
    this.event.city = event.city;
    this.event.country = event.country;
    this.event.coordinate = {
      latitude: event.latitude,
      longitude: event.longitude
    } as CoordinateDTO;
  }

  saveImage() {

    if (!this.croppedImage)
      return;

    var object = {
      content: this.croppedImage
    } as ObjectDTO;

    this.imgLoading = true;
    this.objectsService.uploadImage(object).subscribe((res: StgdrvResponseDTO) => {
        console.log('Res', res)
        this.imgLoading = false;

        this.toastrService.success('Success', res.message);

        this.croppedImage = null;
        this.imgURL = res.uri;
        this.event.images.push( {
          "uri": res.uri
        })

        this.file.nativeElement.value = "";
      }, err => {
        this.toastrService.danger('Error', err.error.message);
        this.imgLoading = false;
      });
  }

  fileChangeEvent(event: any): void {
    this.imgURL = null;
    this.imageChangedEvent = event;
  }

  imageCropped(event: ImageCroppedEvent) {
    this.croppedImage = event.base64;
    console.log('Image cropped');
  }
  imageLoaded() {
      // show cropper
      console.log('Image loaded');
  }
  cropperReady() {
      // cropper ready
  }
  loadImageFailed() {
      // show message
      console.error('Image loading failed');
  }
  deleteImage(index) {
    this.event.images.splice(index, 1);
  }
}
