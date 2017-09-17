import {NgModule} from '@angular/core';
import {RelaysComponent} from './relays.component';
import {RelayStartComponent} from './relay-start/relay-start.component';
import {RelayEditComponent} from './relay-edit/relay-edit.component';
import {RelayDetailComponent} from './relay-detail/relay-detail.component';

const relaysRoutes = [
  {
    path: 'relays', component: RelaysComponent, children: [
    {path: '', component: RelayStartComponent},
    {path: 'new', component: RelayEditComponent},
    {path: ':id', component: RelayDetailComponent},
    {path: ':id/edit', component: RelayEditComponent}
  ]
  }];

@NgModule({})
export class RelaysRoutingModule {
}
