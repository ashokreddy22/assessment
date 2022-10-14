import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminComponent } from './admin/admin.component';
import { IssuedComponent } from './issued/issued.component';
import { NewAadharComponent } from './new-aadhar/new-aadhar.component';
import { ShowAadharComponent } from './show-aadhar/show-aadhar.component';
import { UpdateAadharComponent } from './update-aadhar/update-aadhar.component';
import { UserDahboardComponent } from './user-dahboard/user-dahboard.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';

const routes: Routes = [{"path":"adminLogin",component:AdminLoginComponent},
                        {"path":"admin",component:AdminComponent},
                        {"path":"issued",component:IssuedComponent},
                        {"path":"register",component:UserRegistrationComponent},
                        {"path":"login",component:UserLoginComponent},
                        {"path":"dashboard",component:UserDahboardComponent},
                        {"path":"show",component:ShowAadharComponent},
                        {"path":"new",component:NewAadharComponent},
                        {"path":"update",component:UpdateAadharComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
