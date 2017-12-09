import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StativComponent} from './stativ.component';

describe('StativComponent', () => {
  let component: StativComponent;
  let fixture: ComponentFixture<StativComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StativComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StativComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
