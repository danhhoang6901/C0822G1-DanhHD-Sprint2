import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user";
import {TokenService} from "./token.service";
import {LoginService} from "./login.service";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private httpClient: HttpClient) {
  }

  getCart(id: number): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/bill/cart/" + id);
  }


  addBill(billForm: any): Observable<any> {
    return this.httpClient.post<any>("http://localhost:8080/bill/addBill", billForm);
  }

  addBill2(user: number, quantity: number, product: number): Observable<any> {
    let dto = {
      user: user,
      quantity: quantity,
      product: product
    }
    return this.httpClient.post<any>("http://localhost:8080/bill/addBill", dto);
  }

  minusQuantity(id): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/bill/minus/" + id);
  }

  plusQuantity(id): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/bill/plus/" + id);
  }

  deleteProductInCart(id): Observable<any> {
    return this.httpClient.delete<any>("http://localhost:8080/bill/delete/" + id);
  }

  payment(id, note: string): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/bill/payment/" + id + "?note=" + note);
  }
}
