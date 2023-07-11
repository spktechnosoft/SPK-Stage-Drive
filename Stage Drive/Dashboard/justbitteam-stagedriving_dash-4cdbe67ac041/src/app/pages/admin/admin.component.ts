import * as moment from 'moment';
import { Component, ViewEncapsulation, ViewChild, ElementRef, PipeTransform, Pipe, OnInit, TemplateRef } from '@angular/core';
import { AdminJobsService, AdminNodesService, AdminQueuesService, Results, PagedResults } from '../../api';
import { DomSanitizer } from "@angular/platform-browser";
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { CustomServerDataSource } from '../server.data-source';
import { NbToastrService, NbWindowService } from '@nebular/theme';
import { Location, LocationStrategy, PathLocationStrategy, DatePipe } from '@angular/common';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams,
  HttpResponse, HttpEvent } from '@angular/common/http';

@Component({
  selector: 'ngx-admin',
  templateUrl: './admin.component.html',
  styles: [`
    :host nb-tab {
      padding: 1.25rem;
    }
  `]
})
export class AdminComponent {

  @ViewChild('job', {  }) jobTemplate: TemplateRef<any>;

  queues: any;
  jobs: any;
  loading: any;

  settings = {
    pager : {
      perPage:10,
      display: true
    },
    hideSubHeader: false, 
    actions:{add:false, edit:true, delete:true},
    mode: 'external',
    //attr:{class:"table table-hover"},  //use bootstrap zebra style
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
    columns: {
      id: {
        title: 'ID',
        type: 'text',
        filter: false
      },
      type: {
        title: 'Tipo',
        type: 'text',
        filter: false
      },
      processedBy: {
        title: 'Processato da',
        type: 'text',
        filter: false,
      },
      lastProcess: {
        title: 'Data processamento',
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
      failed: {
        title: 'Data fallimento',
        type: 'text',
        filter: false,
        valuePrepareFunction: (value) => {
          if (value == undefined || value.length == 0)
            return '-';
          else {
            let pipe = new DatePipe('it-IT'); // Use your own locale
            return pipe.transform(value, 'short');
          }
        }
      },
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

  constructor(private adminJobsService: AdminJobsService,
            private adminQueuesService: AdminQueuesService,
            private adminNodesService: AdminNodesService,
            private http: HttpClient,
            private windowService: NbWindowService) {
              
    this.loading = true;
    this.adminQueuesService.getQueues('').subscribe((res: Results) => {
      console.log('Res', res)
      this.loading = false;
      let items = res['data'];
      let q = [];
      items.forEach(item => {
        let id: string = item['id'];
        console.log('item ', item);
        if (id.indexOf("@") < 0) {
          q.push(item);
        }
      });
      this.queues = q;
    });
  }

  fetchJobs(queue) {
    this.loading = true;
    this.adminJobsService.getQueuesJobs('', queue, 'completed', undefined, 0, 20).subscribe((res: PagedResults) => {
      console.log('Res', res)
      this.loading = false;
      
      this.jobs = res['data'];

      this.source = new CustomServerDataSource(this.http, {
        endPoint: environment.endpoint+'/v1/admin/jobs?queue='+queue+'&type=completed',
        pagerLimitKey:"limit",
        pagerPageKey:"page",
        sortDirKey: "order",
        sortFieldKey: "sort",
        totalKey: 'size',
        dataKey: 'data'
      });
    });
  }

  onChangeTab($event) {
    console.log('changed');
    
    this.fetchJobs($event.tabTitle);
  }

  onEdit(event): void {
    console.log('event', event);
    this.windowService.open(
      this.jobTemplate,
      {
        title: 'Job', 
        context: { 
          id: event.data.id,
          job: event.data
        },
        windowClass: 'custom-window'
      },
    );
    /*this.router.navigate(['pages/events', event.data.id]).then( (e) => {
      if (e) {
        console.log("Navigation is successful!");
      } else {
        console.log("Navigation has failed!");
      }
    });*/
  }

  onDelete(event): void {
    if (window.confirm('Sei sicuro di voler procedere?')) {
      /*this.eventsService.deleteEvent(event.data.id, '').subscribe((res: StgdrvResponseDTO) => {
        console.log('Res', res)
        this.source.refresh();
        this.loading = false;
  
        this.toastrService.success('Success', res.message);
      });*/
    } else {
      this.loading = false;
    }
  }


}
