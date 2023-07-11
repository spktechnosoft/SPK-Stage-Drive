import { Injectable } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Location } from '@angular/common';
import { filter } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpParams,
  HttpResponse, HttpEvent }                           from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BundleService {
  public bundle: any;

  private res: Observable<HttpResponse<Object>>;
  private data: Observable<any>;

  constructor(private http: HttpClient) {
    //this.fetchBundle();
  }

  fetchBundle(): Observable<any> {
    if (this.bundle) {
      console.log('Found bundle', this.bundle);
      return Observable.of(this.bundle);
    }
    
    this.data = new Observable(observer => {
        console.log('Fetching bundle');
        this.http.get(environment.bundle, { observe: 'response' })
        .subscribe((data: any) => {
            console.log('Bundle', data.body);
            this.bundle = data.body['data'];

            observer.next(this.bundle);
            observer.complete();
          });
    });

    return this.data;
  }
}