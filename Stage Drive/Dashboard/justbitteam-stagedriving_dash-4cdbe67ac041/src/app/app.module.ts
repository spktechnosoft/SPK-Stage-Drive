/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import { APP_BASE_HREF, registerLocaleData} from '@angular/common';
import { LOCALE_ID } from '@angular/core';
import localeIT from '@angular/common/locales/it';
import localeITExtra from '@angular/common/locales/extra/it';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CoreModule } from './@core/core.module';
import { environment } from '../environments/environment';

import { ApiModule, Configuration, ConfigurationParameters, BASE_PATH } from './api';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ThemeModule } from './@theme/theme.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { NbMomentDateModule } from '@nebular/moment';
import { NbDateFnsDateModule } from '@nebular/date-fns';

import { NbPasswordAuthStrategy, NbAuthModule } from '@nebular/auth';
import { NbSpinnerModule, NbToastrModule, NbDialogModule, NbDatepickerModule, NbButtonModule, NbActionsModule, NbBadgeModule, NbTabsetModule, NbWindowModule, NbListModule } from '@nebular/theme';
import { AuthGuard } from './auth-guard.service';
import { BundleService } from './pages/commons/bundle.service';

import { AgmCoreModule } from '@agm/core';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './token.interceptor';
import { UsersComponent } from './pages/users/users.component';
import { UserEditComponent } from './pages/users/user-edit.component';

import { ImageCropperModule } from 'ngx-image-cropper';

registerLocaleData(localeIT, 'it-IT', localeITExtra);

/*export function apiConfigFactory (): Configuration => {
  const params: ConfigurationParameters = {
    // set configuration parameters here.
  }
  return new Configuration(params);
}*/

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    //HttpClientModule,
    AppRoutingModule,
    ApiModule,//.forRoot(apiConfigFactory),

    NgbModule.forRoot(),
    ThemeModule.forRoot(),
    CoreModule.forRoot(),
    NbButtonModule,
    ImageCropperModule,

    // NbMomentDateModule,
    NbDateFnsDateModule.forRoot({
      format: 'DD/MM/YYYY',
      parseOptions: { awareOfUnicodeTokens: true },
      formatOptions: { awareOfUnicodeTokens: true },
    }),

    NbTabsetModule,
    NbBadgeModule,
    NbListModule,
    Ng2SmartTableModule,
    NbSpinnerModule,
    NbDialogModule.forRoot(),
    NbToastrModule.forRoot(),
    NbDatepickerModule.forRoot(),
    NbWindowModule.forRoot({
      
    }),
    NbActionsModule,

    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBk3v1BxHAxzVkoASXKurNZH-ZBAG_GD2Y',
      libraries: ['geometry', 'places']
    }),

    NbAuthModule.forRoot({
      strategies: [
        NbPasswordAuthStrategy.setup({
          name: 'email',

          baseEndpoint: environment.endpoint,
          login: {
            // ...
            endpoint: '/auth',
          },
          logout: true,
          token: {
            key: 'accessToken', // this parameter tells where to look for the token
          }
        }),
      ],
      forms: {
        login: {
          redirectDelay: 500, // delay before redirect after a successful login, while success message is shown to the user
          strategy: 'email',  // strategy id key.
          rememberMe: false,   // whether to show or not the `rememberMe` checkbox
          showMessages: {     // show/not show success/error messages
            success: true,
            error: true,
          }
        },
        logout: {
          redirectDelay: 500,
          strategy: 'email',
        }
      },
    })
  ],
  bootstrap: [AppComponent],
  providers: [
    { provide: APP_BASE_HREF, useValue: '/' },
    { provide: BASE_PATH, useValue: environment.endpoint },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    { provide: LOCALE_ID, useValue: "it-IT" },
    AuthGuard,
    BundleService
  ],
})
export class AppModule {
}
