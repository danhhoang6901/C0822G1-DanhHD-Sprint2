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
    return this.httpClient.get<any>("http://localhost:8080/cart/" + id);
  }


  addBill2(user: number, quantity: number, product: number, size: string): Observable<any> {
    let dto = {
      user: user,
      quantity: quantity,
      product: product,
      size: size
    }
    return this.httpClient.post<any>("http://localhost:8080/cart/addCart", dto);
  }

  minusQuantity(id): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/cart/minus/" + id);
  }

  plusQuantity(id): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/cart/plus/" + id);
  }

  deleteProductInCart(id): Observable<any> {
    return this.httpClient.delete<any>("http://localhost:8080/cart/delete/" + id);
  }

  buy(id, totalMoney, time): Observable<any> {
    let dto = {
      user: id,
      totalMoney: totalMoney,
      datePurchase: time,
    }
    return this.httpClient.post("http://localhost:8080/cart/buy", dto);
  }
}
