import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ExComponent } from './ex/ex.component';
import {HttpClientModule} from '@angular/common/http';
import { Ex2Component } from './ex2/ex2.component';

@NgModule({
  declarations: [
    AppComponent,
    ExComponent,
    Ex2Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
