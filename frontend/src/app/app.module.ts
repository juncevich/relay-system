import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HeaderComponent} from './core/header/header.component';
import {StativComponent} from './domain/stativ/stativ.component';
import {LeftMenuComponent} from './core/left-menu/left-menu.component';
import {BreadcumbComponent} from './core/breadcumb/breadcumb.component';
import {RelaysModule} from './domain/relays/relays.module';
import {SharedModule} from './shared/shared.module';
import { StationComponent } from './domain/station/station.component';
import {StativModule} from './domain/stativ/stativ.module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LeftMenuComponent,
    BreadcumbComponent,
    StationComponent],
  imports: [
    BrowserModule,
    RelaysModule,
    SharedModule,
    StativModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
