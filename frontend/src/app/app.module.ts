import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RelayItemComponent } from './relay-item/relay-item.component';
import { RelaysComponent } from './relays/relays.component';

@NgModule({
  declarations: [
    AppComponent,
    RelayItemComponent,
    RelaysComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
