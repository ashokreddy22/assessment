import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataServiceService } from '../data-service.service';
import { UserClass } from '../UserClass';

@Component({
  selector: 'app-show-aadhar',
  templateUrl: './show-aadhar.component.html',
  styleUrls: ['./show-aadhar.component.css']
})
export class ShowAadharComponent implements OnInit {
  id:number=4;
  s:string="get";
  users:UserClass[];
  constructor(private router:Router, private service:DataServiceService) { }

  ngOnInit(): void {
     this.getUserById();
  }
  
  getUserById(){
      this.service.getUserById(this.id,this.s).subscribe(result=>this.users=result);
  }
}
