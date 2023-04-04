import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";
import {ProfileComponent} from "./profile/profile.component";
import {BillHistoryComponent} from "../home/bill-history/bill-history.component";


const routes: Routes = [{
  path: "", component: LoginComponent
}, {
  path: "signUp", component: SignupComponent
}, {
  path: "profile", component: ProfileComponent
},
  {
    path: "billHistory/:id",component: BillHistoryComponent
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LogInRoutingModule {
}
