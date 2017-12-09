import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HeaderComponent} from './core/header/header.component';
import {LeftMenuComponent} from './core/left-menu/left-menu.component';
import {BreadcumbComponent} from './core/breadcumb/breadcumb.component';
import {RelaysModule} from './relays/relays.module';
import {SharedModule} from './shared/shared.module';
import {StativModule} from './statives/stativ.module';
import {StationsModule} from './stations/stations.module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LeftMenuComponent,
    BreadcumbComponent],
  imports: [
    BrowserModule,
    RelaysModule,
    SharedModule,
    StativModule,
    StationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
