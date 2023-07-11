import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { NbAuthService, NbAuthSimpleToken } from '@nebular/auth';
import { Observable } from 'rxjs/Observable';
import { environment } from '../environments/environment';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

    public token;

  constructor(public authService: NbAuthService) {
    this.authService.onTokenChange()
    .subscribe((token: NbAuthSimpleToken) => {

      console.log('AccessToken', token);
      
      if (token.isValid()) {
        this.token = token;
      }

    });
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
    if (request.url.startsWith(environment.endpoint)) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.token}`
        }
      });    
    }

    return next.handle(request);
  }
}