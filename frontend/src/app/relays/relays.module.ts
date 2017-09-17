import {NgModule} from '@angular/core';
import {RelayEditComponent} from './relay-edit/relay-edit.component';
import {RelayDetailComponent} from './relay-detail/relay-detail.component';
import {RelayItemComponent} from './relay-item/relay-item.component';
import {RelayListComponent} from './relay-list.component';
import {CommonModule} from '@angular/common';

@NgModule({
  declarations: [
    RelayEditComponent,
    RelayDetailComponent,
    RelayItemComponent,
    RelayListComponent]
  , imports: [CommonModule]
})
export class RelaysModule {
}
