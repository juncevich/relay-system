import {NgModule} from '@angular/core';
import {RelayEditComponent} from './relay-edit/relay-edit.component';
import {RelayDetailComponent} from './relay-detail/relay-detail.component';
import {RelayItemComponent} from './relay-item/relay-item.component';
import {RelayListComponent} from './relay-list/relay-list.component';
import {CommonModule} from '@angular/common';
import {RelaysComponent} from './relays.component';
import {RelayStartComponent} from './relay-start/relay-start.component';
import {ReactiveFormsModule} from '@angular/forms';
import {RelaysRoutingModule} from './relays-routing.module';
import {SharedModule} from '../shared/shared.module';

@NgModule({
  declarations: [
    RelayEditComponent,
    RelayDetailComponent,
    RelayItemComponent,
    RelayListComponent,
    RelaysComponent,
    RelayStartComponent]
  , imports: [
    CommonModule,
    ReactiveFormsModule,
    RelaysRoutingModule,
    SharedModule]
})
export class RelaysModule {
}
