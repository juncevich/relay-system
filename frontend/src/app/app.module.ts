import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RelayItemComponent } from './relays/relay-item/relay-item.component';
import { RelaysComponent } from './relays/relay-detail/relay-detail.component';
import { HeaderComponent } from './header/header.component';
import { StativComponent } from './stativ/stativ.component';
import { LeftMenuComponent } from './left-menu/left-menu.component';
import { BreadcumbComponent } from './breadcumb/breadcumb.component';
import { RelayListComponent } from './relays/relay-list.component';

@NgModule({
  declarations: [
    AppComponent,
    RelayItemComponent,
    RelaysComponent,
    HeaderComponent,
    StativComponent,
    LeftMenuComponent,
    BreadcumbComponent,
    RelayListComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
