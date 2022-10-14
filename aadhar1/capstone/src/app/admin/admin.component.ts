import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { DataServiceService } from '../data-service.service';
import { UserClass } from '../UserClass';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  allbooks:Observable<UserClass[]>;

  //inject the service
  constructor(private router:Router, private service:DataServiceService) { }

  users:UserClass[];
  i:string="s";
  ngOnInit(): void {
   // this.service.getAllUser().subscribe(result=>this.users=result);
     this.getsoftBooks();
     
  }
  getsoftBooks(){
    this.service.getAllUser().subscribe(result=>this.users=result);
  }
  IssuedList(){
    this.router.navigateByUrl('/issued');
    //this.service.getIssuedList(this.i).subscribe(result=>this.users=result);
  }
  BookDelete(id:number){
       this.service.deletePost(id);
       alert("Aadhar Deleted Successfully");
       console.log("user deleted");  
       this.onReload();
  }
  BookUpdate(id:number){
    this.service.updatePost(id);
    alert("Aadhar Approved Successfully");
    console.log("user updated");  
    this.onReload();
}

  onReload(){
    this.router.navigate(['/users']);
    this.ngOnInit(); 
  }

}
