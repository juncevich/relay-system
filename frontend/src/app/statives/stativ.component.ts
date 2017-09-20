import {Component, Input, OnInit, Output} from '@angular/core';
import {RelayItemComponent} from '../relays/relay-item/relay-item.component';

@Component({
  selector: 'app-stativ',
  templateUrl: './stativ.component.html',
  styleUrls: ['./stativ.component.css']
})
export class StativComponent implements OnInit {

  relayArray: RelayItemComponent[];

  constructor() {
  }

  ngOnInit() {
  }

}
