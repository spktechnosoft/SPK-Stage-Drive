import { Component, Input, OnInit } from '@angular/core';
import { ViewCell } from 'ng2-smart-table';
import { DomSanitizer } from '@angular/platform-browser';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { BrandDTO } from '../../api';

@Component({
  template: `
    <a [href]="brandLink">{{brandName}}</a>
  `,
})
export class CatalogBrandRenderComponent implements ViewCell, OnInit {

  brandLink: any;
  brandName: string;
  brand: any;
  catalogId: string;

  @Input() value: any;
  @Input() rowData: any;

  constructor(private sanitizer: DomSanitizer,
              private route: ActivatedRoute) {
    if (this.route.snapshot.paramMap.get('id')) {
      this.catalogId = this.route.snapshot.paramMap.get('id');
    }
  }

  ngOnInit() {
    console.log('Value', this.value);
    // this.value.forEach(element => {
    //   brand += element.name + ' - (' + element.id + ')';
    // });
    this.brand = this.value[0];

    this.brandLink = this.sanitizer.bypassSecurityTrustUrl('#/pages/catalogs/'+this.catalogId+'/brands/'+this.brand.id);
    this.brandName = this.brand.name;
  }

}
