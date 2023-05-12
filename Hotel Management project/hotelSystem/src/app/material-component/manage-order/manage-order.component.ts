import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BillService } from 'src/app/services/bill.service';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';
 

import { saveAs } from "file-saver";

@Component({
  selector: 'app-manage-order',
  templateUrl: './manage-order.component.html',
  styleUrls: ['./manage-order.component.scss']
})
export class ManageOrderComponent implements OnInit {
displayedColumns:string[]=['name','category','price','quantity','total','edit'];
dataSource:any=[];
responseMessage:any;
orderForm:any=FormGroup;
categorys:any=[];
products:any=[];
itemPrice :any;

totalAmount:number=0;



constructor(  private ngxService:NgxUiLoaderService,
  private productService:ProductService,
  private categoryService:CategoryService,
  private billService:BillService,
  private dialog:MatDialog,
  private snackbarService:SnackbarService,
  private router:Router,
  private formBuilder:FormBuilder){}


  ngOnInit(): void {
    this.ngxService.start();
    this.getCategorys();
    this.orderForm=this.formBuilder.group({
      name:[null,[Validators.required,Validators.pattern(GlobalConstants.nameRegex)]],
      email:[null,[Validators.required,Validators.pattern(GlobalConstants.emailRegex)]],
      contactNumber:[null,[Validators.required,Validators.pattern(GlobalConstants.contactNumberRegex)]],
      paymentMethod:[null,[Validators.required]],
      product:[null,[Validators.required]],
      category:[null,[Validators.required]],
      quantity:[null,[Validators.required]],
      price:[null,[Validators.required]],
      total:[null,[Validators.required]]
      
    })
  }
  getCategorys() {
    this.categoryService.getFilterCategory().subscribe((response:any)=>{
      this.ngxService.stop();
      this.categorys=response;
    },(error:any)=>{
      this.ngxService.stop();
      if(error.error?.message){
        this.responseMessage=error.error?.message;
      }else{
        this.responseMessage=GlobalConstants.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage,GlobalConstants.error);
    });


  }


  getProductByCategory(values:any){
   
    this.productService.getProductByCategory(values.id).subscribe((response:any)=>{
      this.products=response;

      this.orderForm.controls['price'].setValue('');
      
      this.orderForm.controls['quantity'].setValue('');
      this.orderForm.controls['total'].setValue(0);
    },(error:any)=>{
    console.log(error);
      if(error.error?.message){
        this.responseMessage=error.error?.message;
      }else{
        this.responseMessage=GlobalConstants.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage,GlobalConstants.error);
    });

  }

  getProductDetails(value:any){

  
    this.productService.getById(value.id).subscribe((response:any)=>{
   
    
      this.itemPrice=response[0].price;

      this.orderForm.controls['price'].setValue(response[0].price);
      
      this.orderForm.controls['quantity'].setValue('1');
      this.orderForm.controls['total'].setValue(this.itemPrice * 1);
    },(error:any)=>{
      console.log(error);
        if(error.error?.message){
          this.responseMessage=error.error?.message;
        }else{
          this.responseMessage=GlobalConstants.genericError;
        }
        this.snackbarService.openSnackBar(this.responseMessage,GlobalConstants.error);
      });
  }

  setQuantity(value:any){
    var temp=this.orderForm.controls['quantity'].value;
    if(temp>0){
      this.orderForm.controls['total'].setValue(this.orderForm.controls['quantity'].value * this.orderForm.controls['price'].value);
    }else if(temp != ''){
      this.orderForm.controls['quantity'].setValue('1');

      this.orderForm.controls['total'].setValue(this.orderForm.controls['quantity'].value * this.orderForm.controls['price'].value);
    }
  }

  forValidateProductAdd(){
    if(this.orderForm.controls['total'].value === 0 || this.orderForm.controls['total'].value === null || this.orderForm.controls['quantity'].value <= 0){
      return true;

    }
    else{
      return false;
    }
  }

  validateSubmit(){
    if(this.totalAmount === 0 || this.orderForm.controls['name'].value === null|| this.orderForm.controls['paymentMethod'].value === null|| this.orderForm.controls['email'].value === null|| this.orderForm.controls['contactNumber'].value === null){
      return true;
    } else{
      return false;
    }

    
  }

  add(){
    var formData=this.orderForm.value;
   

    const productName =  this.dataSource.find((e: { id: any })=> e.id === formData.product.id);
    if(productName === undefined){
      this.totalAmount=this.totalAmount + formData.total;
      this.dataSource.push({id:formData.product.id,name:formData.product.name,category:formData.category.name,quantity:formData.quantity,price:formData.price,total:formData.total});
      this.dataSource = [...this.dataSource];
      // this.orderForm.reset();
      this.snackbarService.openSnackBar(GlobalConstants.productAdded,"success");
    }
    else{
      this.snackbarService.openSnackBar(GlobalConstants.productExistError,GlobalConstants.error);
    }
  }

  handleDeleteAction(value:any,element:any){
   
    this.totalAmount = this.totalAmount - element.total;
    this.dataSource.splice(value,1);
    this.dataSource=[...this.dataSource];
  }
  submitAction(){
    var formData = this.orderForm.value;
    var data = {
      name:formData.name,
      email:formData.email,
      contactNumber:formData.contactNumber,
      paymentMethod:formData.paymentMethod,
      total: this.totalAmount.toString(),
      productDetails : JSON.stringify(this.dataSource)
    }
    this.ngxService.start();
    this.billService.generateReport(data).subscribe((response:any)=>{
      this.downloadFile(response?.uuid);
      this.orderForm.reset();
      this.dataSource=[];
      this.totalAmount=0;
    },(error:any)=>{
      this.ngxService.stop();
      console.log(error);
        if(error.error?.message){
          this.responseMessage=error.error?.message;
        }else{
          this.responseMessage=GlobalConstants.genericError;
        }
        this.snackbarService.openSnackBar(this.responseMessage,GlobalConstants.error);
      });
  }
  downloadFile(filename: any) {
   var data = {
    uuid:filename
   }

   this.billService.getPdf(data).subscribe((response:any)=>{
    saveAs(response,filename + '.pdf');
this.ngxService.stop();
   })
  }
}


