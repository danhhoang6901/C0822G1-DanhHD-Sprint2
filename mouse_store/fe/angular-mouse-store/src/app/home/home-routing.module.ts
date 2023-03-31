import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {BodyComponent} from "./body/body.component";
import {CartComponent} from "./cart/cart.component";
import {DetailComponent} from "./detail/detail.component";
import {UserGuard} from "../log-in/security/user.guard";


const routes: Routes = [{
  path: "", component: BodyComponent
},
  {
    path: "cart", canActivate: [UserGuard], component: CartComponent
  },
  {
    path: "detail/:id", component: DetailComponent
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule {
}
