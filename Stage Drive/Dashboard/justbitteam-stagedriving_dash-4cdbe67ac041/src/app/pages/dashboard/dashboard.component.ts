import * as moment from 'moment';
import { Component, ViewEncapsulation, ViewChild, ElementRef, PipeTransform, Pipe, OnInit } from '@angular/core';

import { DomSanitizer } from "@angular/platform-browser";

@Pipe({ name: 'safe' })
export class SafePipe implements PipeTransform {
  constructor(private sanitizer: DomSanitizer) { }
  transform(url) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }
}

@Component({
  selector: 'ngx-dashboard',
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent {
  startDate: Date;
  startMillis: any;
  finishDate: Date;
  finishMillis: any;

  constructor() {
    this.startDate = moment(new Date()).subtract(5, 'years').toDate();
    this.finishDate = new Date();
  }

  getMetricUrl(id) {
    return 'https://grafana.stagedriving.com/d-solo/4QoAAGvWz/stage-driving?orgId=1&from='+this.startDate.getTime()+'&to='+this.finishDate.getTime()+'&panelId='+id;
  }

}
