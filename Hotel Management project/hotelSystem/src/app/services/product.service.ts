import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient:HttpClient) { }


  addProduct(data:any){
   return  this.httpClient.post("http://localhost:8090/product/addProduct",data,{headers: new HttpHeaders().set('Content.Type',"application/json")});

  }


  updateProduct(data:any){
    return  this.httpClient.post("http://localhost:8090/product/updateProduct",data,{headers: new HttpHeaders().set('Content.Type',"application/json")});
 
   }

   getProducts(){
    return this.httpClient.get("http://localhost:8090/product/getAllProducts");

   }

   updateStatus(data:any){
    return  this.httpClient.post("http://localhost:8090/product/updateStatus",data,{headers: new HttpHeaders().set('Content.Type',"application/json")});
 
   }

   deleteProduct(id:any){
    return  this.httpClient.post("http://localhost:8090/product/deleteProduct/"+id,{headers: new HttpHeaders().set('Content.Type',"application/json")});
   }

getProductByCategory(id:any){
return this.httpClient.get("http://localhost:8090/product/getByCategory/"+id);
}

getById(id:any){
  return this.httpClient.get("http://localhost:8090/product/getById/"+id);
}
}
