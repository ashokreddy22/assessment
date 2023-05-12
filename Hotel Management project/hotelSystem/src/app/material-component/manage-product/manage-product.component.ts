import { Component } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { ProductService } from 'src/app/services/product.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';
import { ConformationComponent } from '../dialog/conformation/conformation.component';
import { ProductComponent } from '../dialog/product/product.component';

@Component({
  selector: 'app-manage-product',
  templateUrl: './manage-product.component.html',
  styleUrls: ['./manage-product.component.scss']
})
export class ManageProductComponent {
displayedColumns:string[]=['name','categoryName','description','price','edit'];
dataSource:any;
//length:any;
responseMessage:any;
 


constructor(  private ngxService:NgxUiLoaderService,
  private productService:ProductService,
  private dialog:MatDialog,
  private snackbarService:SnackbarService,
  private router:Router){}
  ngOnInit(): void {
    this.ngxService.start();
    this.tableData();
  }
  tableData() {
this.productService.getProducts().subscribe((response:any)=>{
  this.ngxService.stop();
  this.dataSource=new MatTableDataSource(response);

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

  applyFilter(event:Event){
    const filterValue=(event.target as HTMLInputElement).value;
    this.dataSource.filter=filterValue.trim().toLowerCase();
  
  }


  handleAddProductAction(){
    const dialogConfig=new MatDialogConfig();
    dialogConfig.data={
      action:'Add'
    };
    dialogConfig.width="800px";
    const dialogRef=this.dialog.open(ProductComponent,dialogConfig);
    this.router.events.subscribe(()=>{
      dialogRef.close();

    });
    const sub=dialogRef.componentInstance.onAddProduct.subscribe((response:any)=>{
      this.tableData();
    })

  }

  handleEditProductAction(values:any){

    const dialogConfig=new MatDialogConfig();
    dialogConfig.data={
      action:'Edit',
      data:values
    };
    dialogConfig.width="800px";
    const dialogRef=this.dialog.open(ProductComponent,dialogConfig);
    this.router.events.subscribe(()=>{
      dialogRef.close();

    });
    const sub=dialogRef.componentInstance.onEditProduct.subscribe((response:any)=>{
      this.tableData();
    })


  }
  handleDeleteProductAction(values:any){
const dialogConfig=new MatDialogConfig();
dialogConfig.data={
  message:'delete'+values.name+' product',
  confirmation:true
}
const dialogRef=this.dialog.open(ConformationComponent,dialogConfig);
const sub=dialogRef.componentInstance.onEmitStatusChange.subscribe((response:any)=>{
  this.ngxService.start();
  this.deleteProduct(values.id);
  dialogRef.close();
  
})
  }

  deleteProduct(id:any){
this.productService.deleteProduct(id).subscribe((response:any)=>{
  this.ngxService.stop();
  this.tableData();
  this.responseMessage=response?.message;
  this.snackbarService.openSnackBar(this.responseMessage,"success")
},(error:any)=>{
  this.ngxService.stop();
  if(error.error?.message){
    this.responseMessage=error.error?.message;
  }else{
    this.responseMessage=GlobalConstants.genericError;
  }
  this.snackbarService.openSnackBar(this.responseMessage,GlobalConstants.error);
})
  }

  onChange(status:any,id:any){
this.ngxService.start();
var data={
  status:status.toString(),
  id:id
}
this.productService.updateStatus(data).subscribe((response:any)=>{
  this.ngxService.stop();
  this.responseMessage=response?.message;
  this.snackbarService.openSnackBar(this.responseMessage,"success")
},(error:any)=>{
  this.ngxService.stop();
  if(error.error?.message){
    this.responseMessage=error.error?.message;
  }else{
    this.responseMessage=GlobalConstants.genericError;
  }
  this.snackbarService.openSnackBar(this.responseMessage,GlobalConstants.error);})
  }
}
