import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  formModel = {
    name: '',
    limit: 1000,
    level: 'Bronze'
  };
  levels = ['Bronze', 'Silver', 'Gold', 'Platinum', 'Diamond', 'Grand Master']

  constructor() { }

  ngOnInit() {
  }

  createNewWallet (){
    console.log(this.formModel);
  }

  isDisabled(){
    return this.formModel.name === '';
  }
}
