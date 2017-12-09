import {NgModule} from '@angular/core';
import {StativModule} from '../statives/stativ.module';
import {StationComponent} from './station.component';
import {ReactiveFormsModule} from '@angular/forms';
import {SharedModule} from '../shared/shared.module';
import {CommonModule} from '@angular/common';

@NgModule({
  declarations: [StationComponent],
  imports: [
    StativModule,
    CommonModule,
    ReactiveFormsModule,
    SharedModule]
})
export class StationsModule {
}
