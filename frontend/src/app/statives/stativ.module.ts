import {NgModule} from '@angular/core';
import {RelaysModule} from '../relays/relays.module';
import {StativComponent} from './stativ.component';
import {SharedModule} from '../shared/shared.module';
import {RelaysRoutingModule} from '../relays/relays-routing.module';
import {ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {RelayStartComponent} from '../relays/relay-start/relay-start.component';
import {RelayItemComponent} from '../relays/relay-item/relay-item.component';
import {StationsModule} from '../stations/stations.module';

@NgModule({
  declarations: [StativComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    RelaysModule]
})
export class StativModule {

}
