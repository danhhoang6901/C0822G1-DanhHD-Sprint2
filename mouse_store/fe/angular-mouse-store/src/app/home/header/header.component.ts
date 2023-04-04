import {Component, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {LoginService} from "../../service/login.service";
import {TokenService} from "../../service/token.service";
import {Router} from "@angular/router";
import {ShareService} from "../../service/share.service";
import {CartService} from "../../service/cart.service";
import {BillDetail} from "../../model/bill-detail";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  user: User;
  role = 'none';
  name = 'Đăng nhập'
  isLogged = false;
  totalQuantity = 0;
  cart: BillDetail[];

  constructor(private cartService: CartService,
              private login: LoginService, private token: TokenService,
              private router: Router, private share: ShareService) {
  }


  ngOnInit(): void {
    this.isLogged = this.token.isLogger()
    this.loader();
    this.getIdUser();
    this.getOrder()
    this.getQuantity();
    this.share.getClickEvent().subscribe(() => {
      this.isLogged = this.token.isLogger()
      this.loader();
      this.getIdUser();
      this.getOrder();
      this.getQuantity();
    })
  }


  getQuantity() {
    this.totalQuantity = 0
    if (this.cart != null) {
      for (let i = 0; i < this.cart.length; i++) {
        this.totalQuantity += this.cart[i].quantity
      }
    }
  }

  getIdUser() {
    this.login.profile1(this.token.getId()).subscribe(next => {
      this.user = next;
      this.getOrder();
    })
  }

  getOrder() {
    this.cartService.getCart(this.user?.id).subscribe(data => {
      this.cart = data;
      this.getQuantity();
    });

  }

  loader() {
    this.isLogged = this.token.isLogger()
    if (this.isLogged) {
      this.login.profile1(this.token.getId()).subscribe(next => {
        this.user = next;
        this.name = this.user?.name;
      })
      this.role = this.token.getRole();
    }
  }

  logout() {
    this.role = 'none';
    this.name = 'Đăng nhập';
    this.isLogged = false;
    this.token.logout();
    this.router.navigateByUrl('/');
    this.share.sendClickEvent();
  }


  checkProfile() {
    if (!this.isLogged) {
      this.router.navigateByUrl('/login')
    } else {
      this.router.navigateByUrl('/profile')
    }
  }
}
