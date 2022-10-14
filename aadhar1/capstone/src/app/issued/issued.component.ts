import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { DataServiceService } from '../data-service.service';
import { UserClass } from '../UserClass';

@Component({
  selector: 'app-issued',
  templateUrl: './issued.component.html',
  styleUrls: ['./issued.component.css']
})
export class IssuedComponent implements OnInit {

  allbooks:Observable<UserClass[]>;

  //inject the service
  constructor(private router:Router, private service:DataServiceService) { }

  users:UserClass[];
  i:string="s";
  ngOnInit(): void {
    //this.IssuedList();
    this.getsoftBooks();
  }
  getsoftBooks(){
    alert("Get Aadhar issued List");
    this.service.getAllUser().subscribe(result=>this.users=result);
  }
  /*IssuedList(){
       this.service.getIssuedList(this.i).subscribe(result=>this.users=result);
  }*/
}
