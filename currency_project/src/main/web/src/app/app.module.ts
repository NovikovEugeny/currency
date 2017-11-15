import {RouterModule} from '@angular/router';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {GuestHeaderComponent} from './guest/guest-header/guest-header.component';
import {GuestCarouselComponent} from './guest/guest-carousel/guest-carousel.component';
import {FooterComponent} from './guest/footer/footer.component';
import {AnalyticsInfoComponent} from './guest/analytics-info/analytics-info.component';
import {SubscriptionsInfoComponent} from './guest/subscriptions-info/subscriptions-info.component';
import {PaymentInfoComponent} from './guest/payment-info/payment-info.component';
import {StartPageComponent} from './start-page/start-page.component';
import {UserPageComponent} from './user-page/user-page.component';
import {SignInModalComponent} from './guest/sign-in-modal/sign-in-modal.component';
import {SignUpModalComponent} from './guest/sign-up-modal/sign-up-modal.component';
import {UserService} from "./_services/user.service";
import {AuthGuard} from "./_services/auth-guard.service";
import { Page404Component } from './error/page-404/page-404.component';
import { UserHeaderComponent } from './user/user-header/user-header.component';
import { NavbarComponent } from './user/navbar/navbar.component';
import { BalanceComponent } from './user/balance/balance.component';

const routes = [
  { path: '', component: StartPageComponent, children: [
    { path: '', component: GuestCarouselComponent },
    { path: 'subscription-info', component: SubscriptionsInfoComponent },
    { path: 'analytics-info', component: AnalyticsInfoComponent },
    { path: 'payment-info', component: PaymentInfoComponent }
  ]},
  { path: 'user', component: UserPageComponent, canActivate: [AuthGuard], children: [
    { path: 'balance', component: BalanceComponent }
  ]},
  { path: '404', component: Page404Component},
  { path: '**', redirectTo: '/404'}
];

@NgModule({
  declarations: [
    AppComponent,
    GuestHeaderComponent,
    GuestCarouselComponent,
    FooterComponent,
    AnalyticsInfoComponent,
    SubscriptionsInfoComponent,
    PaymentInfoComponent,
    StartPageComponent,
    UserPageComponent,
    SignInModalComponent,
    SignUpModalComponent,
    Page404Component,
    UserHeaderComponent,
    NavbarComponent,
    BalanceComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
    NgbModule.forRoot(),
    RouterModule.forRoot(routes)
  ],
  providers: [
    UserService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
