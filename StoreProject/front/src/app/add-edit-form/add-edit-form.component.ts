import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Ad } from '../model/Ad';

@Component({
  selector: 'app-add-edit-form',
  templateUrl: './add-edit-form.component.html',
  styleUrls: ['./add-edit-form.component.css']
})
export class AddEditFormComponent implements OnInit {

  
  @Input()
  adToAdd: Ad;

  @Input()
  isAdministrator:boolean;
  
  @Output()
  savedAd: EventEmitter<Ad>=new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  save(){
    console.log(this.adToAdd);
    this.savedAd.next(this.adToAdd);
  }

  reset(){
    this.adToAdd = {
      title: "",
      description: "",
      readme: "",
      location: "",
      price: 0
    }
    }




}
