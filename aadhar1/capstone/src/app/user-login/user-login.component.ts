import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup,FormBuilder,Validators } from '@angular/forms';
@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  registerForm: FormGroup;
  name:string;
  password:string;
  //declare boolean variable to get form submitted  or not?
  submitted:boolean=false;
  
  constructor(private builder:FormBuilder,private router:Router) { }

  ngOnInit(): void {
    this.registerForm= this.builder.group(
      {
        firstName:["",Validators.required],
        password:["",[Validators.required,Validators.minLength(8)]]
      }
    );
  }
   OnSubmit(){
   
      alert("Enter to Login")
     this.router.navigateByUrl('/dashboard');
    }
    get f(){
      return this.registerForm.controls;
    }
  
}
