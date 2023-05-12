import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient:HttpClient) { }


  addCategory(data:any){
   return this.httpClient.post("http://localhost:8090/category/addNewCategory",data,{ headers:new HttpHeaders().set('Content-Type','application/json')});
  }


  updateCategory(data:any){
    return this.httpClient.post("http://localhost:8090/category/updateCategory",data,{ headers:new HttpHeaders().set('Content-Type','application/json')});
      }

   getCategory(){
        return this.httpClient.get("http://localhost:8090/category/getCategory");
      }



      getFilterCategory(){
        return this.httpClient.get("http://localhost:8090/category/getCategory?filterValue+true");
      }
}
