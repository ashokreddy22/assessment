import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataServiceService } from '../data-service.service';
import { UserClass } from '../UserClass';

@Component({
  selector: 'app-update-aadhar',
  templateUrl: './update-aadhar.component.html',
  styleUrls: ['./update-aadhar.component.css']
})
export class UpdateAadharComponent implements OnInit {

  title = 'CAPSTONE';
  todo={name:"",mobile:"",pass:""}
  todoList:any = [];
  constructor(private crudHttpService:DataServiceService,private router:Router) { }

  ngOnInit(): void {
  }
  user:UserClass= new UserClass();
  isBookAdded = false;
  i:number=1000;
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
     
     this.crudHttpService.updateById(data,this.i).subscribe();
     alert("Aadhar Updated Successfully!!!");
    
  }
}
