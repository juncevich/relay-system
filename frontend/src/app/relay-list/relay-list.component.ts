import {Component, OnInit} from '@angular/core';
import {RelayItemComponent} from './relay-item/relay-item.component';

@Component({
  selector: 'app-relay-list',
  templateUrl: './relay-list.component.html',
  styleUrls: ['./relay-list.component.css']
})
export class RelayListComponent implements OnInit {

  relay_list: Array<RelayItemComponent>[];

  constructor() {
  }

  ngOnInit() {
  }

}
