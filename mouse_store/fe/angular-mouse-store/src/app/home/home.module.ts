import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { BodyComponent } from './body/body.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import {LogInModule} from "../log-in/log-in.module";
import { CartComponent } from './cart/cart.component';
import { DetailComponent } from './detail/detail.component';


@NgModule({
  declarations: [BodyComponent, FooterComponent, HeaderComponent, CartComponent, DetailComponent],
  exports: [
    FooterComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    LogInModule
  ]
})
export class HomeModule { }
