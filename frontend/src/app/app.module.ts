import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HeaderComponent} from './core/header/header.component';
import {StativComponent} from './stativ/stativ.component';
import {LeftMenuComponent} from './core/left-menu/left-menu.component';
import {BreadcumbComponent} from './breadcumb/breadcumb.component';
import {RelaysModule} from './relays/relays.module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    StativComponent,
    LeftMenuComponent,
    BreadcumbComponent],
  imports: [
    BrowserModule,
    RelaysModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
