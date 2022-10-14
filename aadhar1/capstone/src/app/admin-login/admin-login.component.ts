import { Component, OnInit } from '@angular/core';
import { FormGroup,FormBuilder,Validators } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

   //declare formgroup variables
   registerForm: FormGroup;
   name:string;
   password:string;
   //declare boolean variable to get form submitted  or not?
   submitted:boolean=false;
 
   //inject formbuilder dependency to create form with validators
   constructor(private builder:FormBuilder,private router:Router) { }
 
   //when component initiate we will write code here to build form with validators
   ngOnInit(): void {
 
     this.registerForm= this.builder.group(
       {
         firstName:["",Validators.required],
         password:["",[Validators.required,Validators.minLength(8)]]
       }
     );
   }
 
   //when usewr will click on submit button - method will be called
   OnSubmit(){
     this.submitted=true;
     this.name=this.registerForm.get('firstName')?.value;
     this.password=this.registerForm.get('password')?.value;
     if(this.name=='viratkohli' && this.password== 'virat@123')
     {
       alert("Valid credentials and click ok to login!!!");
      this.router.navigateByUrl('/admin');
     }
     else
          alert("please enter credentials correctly");
         
   }
 
   //to access the  form control in a view to display the error
   get f(){
     return this.registerForm.controls;
   }
 

}
