import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAadharComponent } from './show-aadhar.component';

describe('ShowAadharComponent', () => {
  let component: ShowAadharComponent;
  let fixture: ComponentFixture<ShowAadharComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowAadharComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowAadharComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
