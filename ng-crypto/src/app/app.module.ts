import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';

import { WalletViewComponent } from './wallet-view/wallet-view.component';
import { TemplateComponent } from './demos/template/template.component';

import {HttpClientModule} from "@angular/common/http";
import {PricingService} from "./pricing.service";
import { FormComponent } from './demos/form/form.component';
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    AppComponent,

    WalletViewComponent,
    TemplateComponent,
    FormComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,FormsModule
  ],
  providers: [ PricingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
