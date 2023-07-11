import { Component, Input, OnInit } from '@angular/core';

import { NbMenuService, NbSidebarService } from '@nebular/theme';
import { UserData } from '../../../@core/data/users';
import { AnalyticsService } from '../../../@core/utils';
import { NbAuthService, NbAuthSimpleToken, NbTokenService } from '@nebular/auth';
import { CanActivate, Router } from '@angular/router';
import { AccountsService, AccountDTO } from '../../../api';
import {Headers} from '@angular/http';

@Component({
  selector: 'ngx-header',
  styleUrls: ['./header.component.scss'],
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {

  @Input() position = 'normal';

  user: any;

  userMenu = [/*{ title: 'Profile' },*/ { title: 'Esci' }];

  constructor(private sidebarService: NbSidebarService,
              private menuService: NbMenuService,
              private userService: UserData,
              private analyticsService: AnalyticsService,
              private authService: NbAuthService,
              private accountsService: AccountsService,
              private tokenService: NbTokenService,
              private router: Router) {

              this.authService.onTokenChange()
                .subscribe((token: NbAuthSimpleToken) => {

                  console.log('token', token);
                  
                  if (token.isValid()) {
                    console.log('Token is valid');
                    
                    //this.user = token.getPayload(); // here we receive a payload from the token and assigns it to our `user` variable 
                    this.accountsService.getAccount('me', )
                      .subscribe((user: AccountDTO) => {
                        console.log('Fetched user', user)
                        this.user = user
                      }, err => {
                        console.error("Unable to retrieve user", err);
                        this.router.navigate(['auth/login']);
                      });
                  }

                });

              this.menuService.onItemClick().subscribe(( event ) => {
                this.onItemSelection(event.item.title);
              })
  }

  ngOnInit() {
    // this.userService.getUsers()
      // .subscribe((users: any) => this.user = users.nick);
  }

  onItemSelection( title ) {
    if ( title === 'Esci' ) {
      // Do something on Log out
      console.log('Log out Clicked ')

      this.authService.logout('email');
      this.tokenService.clear();
      this.router.navigate(['auth/login']);
    }
  }

  toggleSidebar(): boolean {
    this.sidebarService.toggle(true, 'menu-sidebar');

    return false;
  }

  goToHome() {
    this.menuService.navigateHome();
  }

  startSearch() {
    this.analyticsService.trackEvent('startSearch');
  }
}
