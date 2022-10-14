import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminComponent } from './admin/admin.component';
import { IssuedComponent } from './issued/issued.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserDahboardComponent } from './user-dahboard/user-dahboard.component';
import { ShowAadharComponent } from './show-aadhar/show-aadhar.component';
import { NewAadharComponent } from './new-aadhar/new-aadhar.component';
import { UpdateAadharComponent } from './update-aadhar/update-aadhar.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminLoginComponent,
    AdminComponent,
    IssuedComponent,
    UserRegistrationComponent,
    UserLoginComponent,
    UserDahboardComponent,
    ShowAadharComponent,
    NewAadharComponent,
    UpdateAadharComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
