import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataServiceService } from '../data-service.service';
import { UserClass } from '../UserClass';

@Component({
  selector: 'app-new-aadhar',
  templateUrl: './new-aadhar.component.html',
  styleUrls: ['./new-aadhar.component.css']
})
export class NewAadharComponent implements OnInit {

  title = 'CAPSTONE';
  todo={name:"",mobile:"",pass:""}
  todoList:any = [];
  constructor(private crudHttpService:DataServiceService,private router:Router) { }

  ngOnInit(): void {
  }
  user:UserClass= new UserClass();
  isBookAdded = false;
  createTodo(){
    let data = {
      //id: new Date().getTime(),
      name:this.user.name,
      mobile:this.user.mobile ,
      address:this.user.address,
      dob:this.user.dob,
      dor:this.user.dor,
      gender:this.user.gender,
    }
     
     this.crudHttpService.createnew(data).subscribe();
     alert("Applied new Aadhar Successfully!!!");
    
  }
}
