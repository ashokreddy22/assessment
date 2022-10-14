import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDahboardComponent } from './user-dahboard.component';

describe('UserDahboardComponent', () => {
  let component: UserDahboardComponent;
  let fixture: ComponentFixture<UserDahboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserDahboardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserDahboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
