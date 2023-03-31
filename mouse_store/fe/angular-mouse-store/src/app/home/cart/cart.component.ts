import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {TokenService} from "../../service/token.service";
import {Router} from "@angular/router";
import {ShareService} from "../../service/share.service";
import {CartService} from "../../service/cart.service";
import {BillDetail} from "../../model/bill-detail";
import {User} from "../../model/user";
import {LoginService} from "../../service/login.service";
import Swal from "sweetalert2";
import {render} from 'creditcardpayments/creditCardPayments';

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

  constructor(private tokenService: TokenService, private title: Title,
              private router: Router, private shareService: ShareService,
              private cartService: CartService, private loginService: LoginService) {

  }

  ngOnInit(): void {
    this.title.setTitle('Giỏ hàng')
    this.getIdUser()
    this.shareService.getClickEvent().subscribe(next => {
      this.getIdUser()
    })
  }

  payment() {
    render({
      id: "#myPaypalButtons",
      currency: "USD",
      value: String(this.money),
      onApprove: (details) => {
        // @ts-ignore
        const note: string = document.getElementById('note').value;
        this.cartService.payment(this.user.id, note).subscribe(next => {
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Thanh toán thành công ',
            showConfirmButton: false,
            timer: 2000
          });
          this.clear();
        })
      }
    });
  }

  getIdUser() {
    this.loginService.profile1(this.tokenService.getId()).subscribe(next => {
      this.user = next;
      this.getOrder()
    })
  }

  getOrder() {
    this.cartService.getCart(this.user?.id).subscribe(data => {
      this.cart = data;
      for (let i = 0; i < data?.length; i++) {
        this.totalPrice += (this.cart[i].quantity * this.cart[i].product.price);
        this.totalQuantity += this.cart[i].quantity;
      }
      this.money = +(this.totalPrice / 23000).toFixed(2);
      this.payment();
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

  minus(id: number) {
    const quantity = +document.getElementById('quantity' + id).innerHTML;
    if (quantity !== 1) {
      this.cartService.minusQuantity(id).subscribe(data => {
        this.totalPrice = 0;
        this.cart.forEach(value => {
          if (value.id === data.id) {
            value.quantity = data.quantity;
          }
        });
        this.total();
        this.money = +(this.totalPrice / 23000).toFixed(2);
      });
    }
  }

  plus(id: number) {
    this.cartService.plusQuantity(id).subscribe(data => {
      this.totalPrice = 0;
      this.cart.forEach(value => {
        if (value.id === data.id) {
          value.quantity = data.quantity;
        }
      });
      this.total();
      this.money = +(this.totalPrice / 23000).toFixed(2);
      
    });
  }

  clear() {
    this.cart = [];
    this.totalPrice = 0;
    this.totalQuantity = 0;
  }

  deleteProductInCart(id: number, name: string) {
    Swal.fire({
      title: 'Bạn Có Muốn Xóa?',
      text: 'Sản phẩm ' + name + ' này khỏi giỏ hàng không ?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#BBBBBB',
      confirmButtonText: 'Có',
      cancelButtonText: 'Không'
    }).then((result) => {
      if (result.isConfirmed) {
        this.cartService.deleteProductInCart(id).subscribe(() => {
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Xóa Thành Công ',
            showConfirmButton: false,
            timer: 2000
          });
          this.getOrder();
          this.clear();
        }, error => {
          console.log(error);
        });
      }
    });
  }
}
