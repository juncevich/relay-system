import {NgModule} from '@angular/core';
import {RelayEditComponent} from './relay-edit/relay-edit.component';
import {RelayDetailComponent} from './relay-detail/relay-detail.component';
import {RelayItemComponent} from './relay-item/relay-item.component';
import {RelayListComponent} from './relay-list/relay-list.component';
import {CommonModule} from '@angular/common';
import { RelaysComponent } from './relays.component';
import { RelayStartComponent } from './relay-start/relay-start.component';

@NgModule({
  declarations: [
    RelayEditComponent,
    RelayDetailComponent,
    RelayItemComponent,
    RelayListComponent,
    RelaysComponent,
    RelayStartComponent]
  , imports: [CommonModule]
})
export class RelaysModule {
}
