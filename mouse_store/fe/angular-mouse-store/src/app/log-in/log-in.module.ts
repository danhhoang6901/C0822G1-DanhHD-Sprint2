import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {LogInRoutingModule} from './log-in-routing.module';
import {LoginComponent} from './login/login.component';
import {ReactiveFormsModule} from "@angular/forms";
import {SignupComponent} from './signup/signup.component';
import { ProfileComponent } from './profile/profile.component';


@NgModule({
  declarations: [LoginComponent, SignupComponent, ProfileComponent],
  exports: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    LogInRoutingModule,
    ReactiveFormsModule
  ]
})
export class LogInModule {
}
