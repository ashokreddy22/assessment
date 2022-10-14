import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewAadharComponent } from './new-aadhar.component';

describe('NewAadharComponent', () => {
  let component: NewAadharComponent;
  let fixture: ComponentFixture<NewAadharComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewAadharComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewAadharComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
