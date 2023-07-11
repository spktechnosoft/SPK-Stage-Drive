import { Component, Input, OnInit } from '@angular/core';
import { ViewCell } from 'ng2-smart-table';
import { DomSanitizer } from '@angular/platform-browser';
import { AccountDTO } from '../../api';

@Component({
  template: `
    <a [href]="accountLink">{{accountName}}</a>
  `,
})
export class UserRenderComponent implements ViewCell, OnInit {

  accountLink: any;
  accountName: string;

  @Input() value: any;
  @Input() rowData: any;

  constructor(private sanitizer: DomSanitizer) {
    
  }

  ngOnInit() {
    this.accountLink = this.sanitizer.bypassSecurityTrustUrl('#/pages/users/'+this.value.id);
    this.accountName = this.value.firstname + ' ' + this.value.lastname;
  }

}
