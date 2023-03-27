import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {EmployeeGuard} from "./log-in/security/employee.guard";
import {AdminGuard} from "./log-in/security/admin.guard";


const routes: Routes = [
  {
    path: "", loadChildren: () => import("./home/home-routing.module").then(module => module.HomeRoutingModule)
  },
  {
    path: "login",
    loadChildren: () => import("./log-in/log-in-routing.module").then(module => module.LogInRoutingModule)
  },
  {
    path: "product",
    loadChildren: () => import("./product/product-routing.module").then(module => module.ProductRoutingModule)
  }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
