import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { saveAs } from 'file-saver';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BillService } from 'src/app/services/bill.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';
import { ConformationComponent } from '../dialog/conformation/conformation.component';
import { ViewBillProductsComponent } from '../dialog/view-bill-products/view-bill-products.component';

@Component({
  selector: 'app-view-bill',
  templateUrl: './view-bill.component.html',
  styleUrls: ['./view-bill.component.scss']
})
export class ViewBillComponent implements OnInit{
  displayedColumns:string[]=['name','email','contactNumber','paymentMethod','total','view'];
dataSource:any=[];
responseMessage:any;
constructor( private ngxService:NgxUiLoaderService,private billService:BillService,private dialog:MatDialog,private snackbarService:SnackbarService,private router:Router){}


  ngOnInit():void {
    this.ngxService.start(); 
    this.tableData();
  }
  
  tableData() {
    this.billService.getBills().subscribe((response:any)=>{
    this.ngxService.stop();
    this.dataSource=new MatTableDataSource(response);
console.log(this.dataSource)
    },(error:any)=>
    {
      this.ngxService.stop();
      console.log(error);
        if(error.error?.message){
          this.responseMessage=error.error?.message;
        }else{
          this.responseMessage=GlobalConstants.genericError;
        }
        this.snackbarService.openSnackBar(this.responseMessage,GlobalConstants.error);
    }
  );
  }

  applyFilter(event:Event){
    const filterValue=(event.target as HTMLInputElement).value;
    this.dataSource.filter=filterValue.trim().toLowerCase();

  }


  handleViewAction(values:any){
    const dialogConfig=new MatDialogConfig();
    dialogConfig.data={
      data:values
    };
    dialogConfig.width="100%";
    const dialogRef=this.dialog.open(ViewBillProductsComponent,dialogConfig);
    this.router.events.subscribe(()=>{
      dialogRef.close();

    });


  }

  handleDeleteAction(values:any){
    const dialogConfig=new MatDialogConfig();
    dialogConfig.data={
      message:'delete  '+values.name +'  bill',
      confirmation:true
    };
   
    const dialogRef=this.dialog.open(ConformationComponent,dialogConfig);
const sub=dialogRef.componentInstance.onEmitStatusChange.subscribe((Response:any)=>{
  this.ngxService.start();
  
  this.deleteBill(values);
  dialogRef.close();
})
  }
  deleteBill(values:any) {
this.billService.delete(values.id).subscribe((Response:any)=>{
  this.ngxService.stop();
  this.tableData();
  this.responseMessage=Response?.message;
  this.snackbarService.openSnackBar(this.responseMessage,"success")
},(error:any)=>
{
  this.ngxService.stop();
  console.log(error);
    if(error.error?.message){
      this.responseMessage=error.error?.message;
    }else{
      this.responseMessage=GlobalConstants.genericError;
    }
    this.snackbarService.openSnackBar(this.responseMessage,GlobalConstants.error);
}
);
  }

  downloadReportaction(values:any){
    this.ngxService.start();
    var data={
      name:values.name,
      email:values.email,
      uuid:values.uuid,
      contactNumber:values.contactNumber,
      paymentMethod:values.paymentMethod,
      total:values.totalAmount.toString(),
      productDetails:values.productDetail,
    }
    this.downloadfile(values.uuid,data)
  }

  downloadfile(fileName:string,data:any){
    this.billService.getPdf(data).subscribe((response:any)=>{
      saveAs(response,fileName+' .pdf');
      this.ngxService.stop();
    })
  }
}
