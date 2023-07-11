import { Injectable } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';
import { ServerDataSource } from 'ng2-smart-table';
import { map } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpParams,
  HttpResponse, HttpEvent }                           from '@angular/common/http';

@Injectable()
export class CustomServerDataSource extends ServerDataSource {

  addPagerRequestParams(httpParams: HttpParams): HttpParams {
    if (this.pagingConf && this.pagingConf['page'] && this.pagingConf['perPage']) {
       httpParams = httpParams.set(this.conf.pagerPageKey, this.pagingConf['page'] + (-1));
       httpParams = httpParams.set(this.conf.pagerLimitKey, this.pagingConf['perPage']);
   }
   return httpParams;
}

  // lastRequestCount: number = 0;

  // constructor(protected http: HttpClient) {
  //   super(http);
  // }

  // count(): number {
  //   return this.lastRequestCount;
  // }

  // getElements(): Promise<any> {
  //   let url = 'http://localhost:8080/v1/accounts?';

  //   if (this.sortConf) {
  //     this.sortConf.forEach((fieldConf) => {
  //       url += `_sort=${fieldConf.field}&_order=${fieldConf.direction.toUpperCase()}&`;
  //     });
  //   }

  //   if (this.pagingConf && this.pagingConf['page'] && this.pagingConf['perPage']) {
  //     url += `page=${this.pagingConf['page']}&limit=${this.pagingConf['perPage']}&`;
  //   }

  //   if (this.filterConf.filters) {
  //     this.filterConf.filters.forEach((fieldConf) => {
  //       if (fieldConf['search']) {
  //         url += `${fieldConf['field']}_like=${fieldConf['search']}&`;
  //       }
  //     });
  //   }

  //   return this.http.get(url, { observe: 'response' })
  //     .pipe(
  //       map(res => {
  //         this.lastRequestCount = +res.headers.get('x-total-count');
  //         return res.body;
  //       })
  //     ).toPromise();
  // }
}