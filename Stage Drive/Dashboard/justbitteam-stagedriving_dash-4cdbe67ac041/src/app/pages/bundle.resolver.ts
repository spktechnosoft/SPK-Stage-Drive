import { Injectable } from '@angular/core';

import { Resolve } from '@angular/router';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/delay';
import { BundleService } from '../pages/commons/bundle.service';

@Injectable()
export class BundleResolver implements Resolve<Observable<string>> {
  constructor(
    private bundleService: BundleService
  ) {}

  resolve() {
    return this.bundleService.fetchBundle();
  }
}