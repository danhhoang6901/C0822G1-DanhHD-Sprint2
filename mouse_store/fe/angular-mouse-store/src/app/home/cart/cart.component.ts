import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {TokenService} from "../../service/token.service";
import {Router} from "@angular/router";
import Swal from "sweetalert2";
import {ShareService} from "../../service/share.service";
import {CartService} from "../../service/cart.service";
import {BillDetail} from "../../model/bill-detail";
import {User} from "../../model/user";
import {LoginService} from "../../service/login.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cart: BillDetail[];
  user: User;
  money: number;
  totalPrice = 0;
  length = 0;
  totalQuantity = 0;
  isLogged = false;

  constructor(private tokenService: TokenService, private title: Title,
              private router: Router, private shareService: ShareService,
              private cartService: CartService, private loginService: LoginService) {
  }

  ngOnInit(): void {
    this.title.setTitle('Giỏ hàng')
    this.loader()
    this.shareService.getClickEvent().subscribe(next => {
      this.loader()
    })
  }

  loader() {
    this.loginService.profile1(this.tokenService.getId()).subscribe(next => {
      this.user = next
      this.getOrder();
    })
  }

  getOrder() {
    this.cartService.getCart(this.user?.id).subscribe(data => {
      this.cart = data;
      console.log(' data ' + data)
      for (let i = 0; i < data?.length; i++) {
        this.totalPrice += (this.cart[i].quantity * this.cart[i].product.price);
      }
      for (let i = 0; i < data?.length; i++) {
        this.totalQuantity += this.cart[i].quantity;
      }
    });
  }

  total() {
    if (this.cart) {
      this.totalPrice = 0;
      this.totalQuantity = 0;
      this.cart.forEach(next => {
        this.totalPrice += next.quantity * next.product.price;
        this.totalQuantity += next.quantity;
      })
    } else {
      this.totalPrice = 0;
    }
  }

}
