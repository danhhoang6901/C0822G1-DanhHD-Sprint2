import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {TokenService} from "../../service/token.service";
import {Router} from "@angular/router";
import {Cart} from "../../model/cart";
import Swal from "sweetalert2";
import {ShareService} from "../../service/share.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  carts: Cart[];
  total = 0;
  length = 0;
  quantity = 0;

  constructor(private tokenService: TokenService, private title: Title, private router: Router, private shareService: ShareService) {
  }

  ngOnInit(): void {
    this.title.setTitle('Giỏ hàng')
    if (this.tokenService.getCart() == undefined) {
      this.length = 0;
    } else {
      this.carts = this.tokenService.getCart();
      this.loading();
      this.length = this.carts.length;
    }
  }

  loading() {
    this.quantity = 0;
    this.total = 0
    if (this.tokenService.getCart() == undefined) {
      this.length = 0;
    } else {
      this.carts = this.tokenService.getCart();
      this.length = this.carts.length;
    }
    for (let i = 0; i < this.carts.length; i++) {
      this.quantity += this.carts[i].quantity
      this.total += (this.carts[i].quantity * this.carts[i].price)
    }
  }

  buy() {

    if (this.length == 0) {
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: 'Bạn chưa thêm sản phẩm vào giỏ hàng ',
        showConfirmButton: false,
        timer: 2000
      });
    } else {
      this.length = 0
      this.total = 0;
      this.carts = []
      this.quantity = 0;
      this.tokenService.setCart(this.carts)
      // this.shareService.sendClickEvent();
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Thanh toán thành công ',
        showConfirmButton: false,
        timer: 2000
      });

    }
  }

  upQuantity(index: number) {
    this.carts[index].quantity += 1;
    this.tokenService.setCart(this.carts)
    this.loading();
    this.shareService.sendClickEvent()
  }

  downQuantity(index: number) {
    if (this.carts[index].quantity == 1) {
      this.carts.splice(index, 1)
    } else {
      this.carts[index].quantity -= 1;
    }
    this.tokenService.setCart(this.carts)
    this.loading();
    this.shareService.sendClickEvent()
  }

  delete(index) {
    this.carts.splice(index, 1);
    this.tokenService.setCart(this.carts)
    this.loading();
  }
}
