import {NgModule} from '@angular/core';
import {RelaysModule} from '../relays/relays.module';
import {StativComponent} from './stativ.component';
import {SharedModule} from '../shared/shared.module';
import {ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';

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
