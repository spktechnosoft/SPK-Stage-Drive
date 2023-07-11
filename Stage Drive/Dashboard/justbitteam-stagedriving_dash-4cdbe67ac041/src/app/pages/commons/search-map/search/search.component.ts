import { Component, ElementRef, EventEmitter, NgZone, OnInit, Output, ViewChild } from '@angular/core';
import { MapsAPILoader } from '@agm/core';
import { Location } from '../entity/Location';

@Component({
  selector: 'ngx-search',
  templateUrl: './search.component.html',
})
export class SearchComponent implements OnInit {

  @Output() positionChanged = new EventEmitter<Location>();

  @ViewChild('search')
  public searchElementRef: ElementRef;

  constructor(private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone) {
  }

  ngOnInit() {
    // load Places Autocomplete
    this.mapsAPILoader.load().then(() => {
      const autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
        types: ['geocode', 'establishment'],
      });
      autocomplete.setFields(['address_component', 'geometry', 'formatted_address']);
      autocomplete.addListener('place_changed', () => {
        this.ngZone.run(() => {
          // get the place result
          const place: google.maps.places.PlaceResult = autocomplete.getPlace();
          let country = null;
          let city = null;
          let town = null;
          let zipcode = null;

          // verify result
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }

          console.log('Place selected', place);

          // Get each component of the address from the place details,
          // and then fill-in the corresponding field on the form.
          for (var i = 0; i < place.address_components.length; i++) {
            var addressType = place.address_components[i].types[0];
            console.log('Address type', addressType);
            console.log('Address', place.address_components[i]);

            /*var field = jQuery('input[data-gtype="' + addressType + '"]');
            if (field) {
                field.val(place.address_components[i]['long_name']);
            }*/
            if (addressType == 'locality') {
              city = place.address_components[i]['long_name'];
            } else if (addressType == 'administrative_area_level_3') {
              town = place.address_components[i]['long_name'];
            } else if (addressType == 'country') {
              country = place.address_components[i]['long_name'];
            } else if (addressType == 'postal_code') {
              zipcode = place.address_components[i]['long_name'];
            }
          }

          const loc: Location = new Location(place.geometry.location.lat(),
              place.geometry.location.lng(), place.formatted_address, country, city, zipcode, town);

          this.positionChanged.emit(loc);
        });
      });
    });
  }
}
