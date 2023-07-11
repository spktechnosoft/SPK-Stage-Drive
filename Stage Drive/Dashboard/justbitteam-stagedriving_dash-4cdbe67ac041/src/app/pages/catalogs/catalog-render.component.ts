import { Component, Input, OnInit } from '@angular/core';
import { ViewCell } from 'ng2-smart-table';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  template: `
    <a [href]="eventLink">{{eventName}}</a>
  `,
})
export class CatalogRenderComponent implements ViewCell, OnInit {

  eventLink: any;
  eventName: string;

  @Input() value: any;
  @Input() rowData: any;

  constructor(private sanitizer: DomSanitizer) {
    
  }

  ngOnInit() {
    this.eventLink = this.sanitizer.bypassSecurityTrustUrl('#/pages/events/'+this.value.id);
    this.eventName = this.value.name;
  }

}
