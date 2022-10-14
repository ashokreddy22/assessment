import { Injectable } from '@angular/core';
import{HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http'
import { Observable, throwError } from 'rxjs';
import { UserClass } from './UserClass';
import { catchError } from 'rxjs/operators';
import { register } from './register';
@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  url:string="http://localhost:8082/api/admin/";
  url1:string="http://localhost:8082/api/user/";
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  

  //inject the DI
  constructor(private http: HttpClient) { }


  //get all users
  getAllUser():Observable<UserClass[]>{
      return this.http.get<UserClass[]>(this.url);
  }
  getAllUserLogin(i:string):Observable<register[]>{
    let endPoints=i;
    return this.http.get<register[]>(this.url+endPoints);
  }
  getUserById(i:number,s:string):Observable<UserClass[]>{
    let endPoints=i;
    let str=s;
    return this.http.get<UserClass[]>(this.url+endPoints);
}
  getIssuedList(i:string):Observable<UserClass[]>{
    let endPoints=i;
    return this.http.get<UserClass[]>(this.url+endPoints);
  }
  //delete user by id
  deletePost(id:number) {
    let endPoints = id;
    this.http.delete(this.url + endPoints).subscribe(data => {
       return this.getAllUser();
    });
  }
  //update user by id
  updatePost(id:number){
    let endPoints=id;
    this.http.put(this.url+endPoints,null).subscribe()
  }
  
  //post
  create(data: any,i:string): Observable<any> {
    let endPoints=i
    return this.http.post(this.url+endPoints, data).pipe(
      catchError(this.handleError)
      );
  }
  updateById(data: any,i:number): Observable<any> {
    let endPoints=i
    return this.http.put(this.url1+endPoints, data).pipe(
      catchError(this.handleError)
      );
  }
  createnew(data:any): Observable<any> {
    return this.http.post(this.url, data).pipe(
      catchError(this.handleError)
      );
  }


   // Handle API errors
  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return throwError(
      'Something bad happened; please try again later.');
  };

}
