import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RelayItemComponent} from './relay-item.component';

describe('RelayItemComponent', () => {
  let component: RelayItemComponent;
  let fixture: ComponentFixture<RelayItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RelayItemComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RelayItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
