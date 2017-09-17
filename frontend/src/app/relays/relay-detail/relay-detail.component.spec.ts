import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RelaysComponent } from './relay-detail.component';

describe('RelaysComponent', () => {
  let component: RelaysComponent;
  let fixture: ComponentFixture<RelaysComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RelaysComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RelaysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
