import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BillService {

  constructor(private httpClient:HttpClient) { }

  generateReport(data:any){
    console.log(data);
    return this.httpClient.post("http://localhost:8090/bill/generateReport",data,{headers:new HttpHeaders().set('Content-type',"application/json")});
  }

  getPdf(data:any):Observable<Blob>{
    return this.httpClient.post("http://localhost:8090/bill/getPdf",data,{responseType:'blob'});
  }

  getBills(){
   return  this.httpClient.get("http://localhost:8090/bill/getBills");
  }


  delete(id:any){
    return this.httpClient.post("http://localhost:8090/bill/deleteBill/"+id,{headers:new HttpHeaders().set('Content-Type',"application/json")})
  }
}
