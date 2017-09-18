import {Component, Input, OnInit} from '@angular/core';
import {RelayItemComponent} from '../relays/relay-item/relay-item.component';

@Component({
  selector: 'app-stativ',
  templateUrl: './stativ.component.html',
  styleUrls: ['./stativ.component.css']
})
export class StativComponent implements OnInit {

  @Input() relayArray: RelayItemComponent[];

  constructor() {
  }

  ngOnInit() {
  }

}
