import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RelayStartComponent } from './relay-start.component';

describe('RelayStartComponent', () => {
  let component: RelayStartComponent;
  let fixture: ComponentFixture<RelayStartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RelayStartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RelayStartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
