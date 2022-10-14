import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataServiceService } from '../data-service.service';
import { register } from '../register';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {
  title = 'CAPSTONE';
  todo={mobile:"",pass:""}
  todoList:any = [];
  i:string="register";
  constructor(private crudHttpService:DataServiceService,private router:Router) { }

  ngOnInit(): void {
  }
  
  user:register= new register();
  isBookAdded = false;
  createTodo(){
    let data = {
      //id: new Date().getTime(),
      mobile:this.user.mobile ,
      password:this.user.pass,
    }
     
     this.crudHttpService.create(data,this.i).subscribe();
     alert("Registered Successfully!!!");
    
  }

}
