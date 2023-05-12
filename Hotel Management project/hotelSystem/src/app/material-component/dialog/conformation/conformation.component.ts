import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-conformation',
  templateUrl: './conformation.component.html',
  styleUrls: ['./conformation.component.scss']
})
export class ConformationComponent implements OnInit {
onEmitStatusChange=new EventEmitter();
details:any={};

constructor(@Inject(MAT_DIALOG_DATA) public dialogData:any){}

  ngOnInit(): void {

    if(this.dialogData && this.dialogData.confirmation){
      this.details=this.dialogData;
    }
 
  }

  handleChangeAction(){
    this.onEmitStatusChange.emit();
  }
}
