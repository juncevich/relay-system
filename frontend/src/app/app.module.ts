import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {StativComponent} from './stativ/stativ.component';
import {LeftMenuComponent} from './left-menu/left-menu.component';
import {BreadcumbComponent} from './breadcumb/breadcumb.component';
import {RelaysModule} from './relays/relays.module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    StativComponent,
    LeftMenuComponent,
    BreadcumbComponent,
    RelaysModule],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
