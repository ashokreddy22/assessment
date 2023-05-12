import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
// url=environment.apiUrl
  constructor(private httpClient: HttpClient) { }


  signup(data:any){
   
    return this.httpClient.post(
      "http://localhost:8090/user/signUp",data,{
        headers:new HttpHeaders().set('Content-Type','application/json')})
  }

  forgotPassword(data:any){
    
    return this.httpClient.post(
      "http://localhost:8090/user/forgotPassword",data,{
        headers:new HttpHeaders().set('Content-Type','application/json')})
  }
  login(data:any){
    
    return this.httpClient.post(
      "http://localhost:8090/user/login",data,{
        headers:new HttpHeaders().set('Content-Type','application/json')})
  }


  checkToken(){
    
   return this.httpClient.get("http://localhost:8090/user/checkToken");
  }


  changePassword(data:any){
    
    return this.httpClient.post(
      "http://localhost:8090/user/changePassword",data,{
        headers:new HttpHeaders().set('Content-Type','application/json')})
  }
getUsers(){
  return this.httpClient.get("http://localhost:8090/user/get",{headers:new HttpHeaders().set('Content-Type','application/json')})
}
updateStatus(data:any){
  return this.httpClient.post("http://localhost:8090/user/update",data,{headers:new HttpHeaders().set('Content-Type','application/json')});
}
}
