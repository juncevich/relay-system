import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HeaderComponent} from './core/header/header.component';
import {StativComponent} from './stativ/stativ.component';
import {LeftMenuComponent} from './core/left-menu/left-menu.component';
import {BreadcumbComponent} from './core/breadcumb/breadcumb.component';
import {RelaysModule} from './relays/relays.module';
import {SharedModule} from './shared/shared.module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    StativComponent,
    LeftMenuComponent,
    BreadcumbComponent],
  imports: [
    BrowserModule,
    RelaysModule,
    SharedModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
