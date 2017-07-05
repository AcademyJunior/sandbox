import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExampleFeatureComponent } from './example-feature.component';

describe('ExampleFeatureComponent', () => {
  let component: ExampleFeatureComponent;
  let fixture: ComponentFixture<ExampleFeatureComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExampleFeatureComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExampleFeatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
