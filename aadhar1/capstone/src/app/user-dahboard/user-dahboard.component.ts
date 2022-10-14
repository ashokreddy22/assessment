import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-dahboard',
  templateUrl: './user-dahboard.component.html',
  styleUrls: ['./user-dahboard.component.css']
})
export class UserDahboardComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  ShowAadhar(){
    alert("show Aadhar Card");
    this.router.navigateByUrl('/show');
  }

  ApplyNewAadhar(){
    alert("Apply for New Aadhar Card");
    this.router.navigateByUrl('/new');
  }

  UpdateAadhar(){
    alert("Update Aadhar Card");
    this.router.navigateByUrl('/update');
  }
}
