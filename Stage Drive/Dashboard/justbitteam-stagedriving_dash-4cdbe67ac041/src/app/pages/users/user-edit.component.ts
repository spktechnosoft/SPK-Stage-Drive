import { Component } from '@angular/core';
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { AccountsService, AccountDTO, StgdrvResponseDTO } from '../../api';
import { CustomServerDataSource } from '../server.data-source';
import { HttpClient, HttpHeaders, HttpParams,
  HttpResponse, HttpEvent }                           from '@angular/common/http';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { NbToastrService } from '@nebular/theme';
import { BundleService } from '../commons/bundle.service';

@Component({
  selector: 'ngx-user-edit',
  templateUrl: './user-edit.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `]
})
export class UserEditComponent {

  user;
  loading = false;

  categories: any[] = [];
  categoriesKeys: any[] = [];
  styles: any[] = [];
  stylesKeys: any[] = [];
  bundle: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private accountsService: AccountsService,
    private toastrService: NbToastrService,
    private bundleService: BundleService
  ) {
    this.bundle = bundleService.bundle;
    
    var items = this.bundle['event-category'].items;
    items.forEach(item => {
      console.log('item', item);
      this.categories.push({
        'value': item.name,
        'title': item.description
      });
      this.categoriesKeys.push(item.name);
    });
    console.log('categories', this.categories);

    var items = this.bundle['style'].items;
    items.forEach(item => {
      console.log('item', item);
      this.styles.push({
        'value': item.name,
        'title': item.description
      });
      this.stylesKeys.push(item.name);
    });
    console.log('style', this.styles);
    
  }

  ngOnInit() {
    let id = this.route.snapshot.paramMap.get('id');
    console.log('init', id);

    this.loading = true;
    this.accountsService.getAccount(id)
      .subscribe((user: AccountDTO) => {
        console.log('User', user)
        this.user = user
        this.loading = false;
      });
  }

  save() {
    console.log('Save');

    this.loading = true;
    this.accountsService.modifyAccount(this.user, this.user.id).subscribe((res: StgdrvResponseDTO) => {
      console.log('Res', res)
      this.loading = false;

      this.toastrService.success('Success', res.message);
    });
  }
}
