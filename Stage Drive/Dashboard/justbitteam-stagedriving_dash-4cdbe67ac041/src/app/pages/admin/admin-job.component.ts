import * as moment from 'moment';
import { Component, ViewEncapsulation, ViewChild, ElementRef, PipeTransform, Pipe, OnInit, Input } from '@angular/core';
import { AdminJobsService, AdminNodesService, AdminQueuesService, Results, PagedResults } from '../../api';
import { DomSanitizer } from "@angular/platform-browser";
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { CustomServerDataSource } from '../server.data-source';
import { Location, LocationStrategy, PathLocationStrategy, DatePipe } from '@angular/common';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams,
  HttpResponse, HttpEvent } from '@angular/common/http';

@Component({
  selector: 'ngx-admin-job',
  templateUrl: './admin-job.component.html',
  styles: [`
    :host nb-tab {
      padding: 1.25rem;
    }
  `]
})
export class AdminJobComponent {

  queue: any;
  @Input() job: any;
  logs: Array<any>;
  @Input() id: string;
  loading: any;

  constructor(private adminJobsService: AdminJobsService,
            private adminQueuesService: AdminQueuesService,
            private adminNodesService: AdminNodesService,
            private http: HttpClient) {
              
    this.loading = true;
  }

  ngOnInit() {

    console.log('Job', this.job);
    this.adminJobsService.getJobLog(this.id, '').subscribe((res: PagedResults) => {
      console.log('Logs', res)
      this.loading = false;

      this.logs = res as Array<any>;
      this.logs.forEach(row => {
        if (row.level == 'INFO') {
          row.type = 'success';
        }
        //ng-class="{'label-info': row.level === 'INFO', 'label-warn': row.level === 'WARNING', 'label-danger': row.level === 'ERROR', 'label-primary': row.level === 'DEBUG', 'label-default': row.level === 'VERBOSE'}"
      });
      
    });
  }

}
