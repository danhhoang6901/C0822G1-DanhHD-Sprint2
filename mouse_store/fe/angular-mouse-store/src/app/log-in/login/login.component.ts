import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Title} from "@angular/platform-browser";
import {Router} from "@angular/router";
import Swal from "sweetalert2";
import {LoginService} from "../../service/login.service";
import {TokenService} from "../../service/token.service";
import {ShareService} from "../../service/share.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  name = 'Thông tin cá nhân';
  message = ''
  form = new FormGroup({
    username: new FormControl(),
    password: new FormControl(),
    rememberMe: new FormControl(true)
  })
  islogged = false;
  constructor(private title:Title,private loginService: LoginService, private token: TokenService, private router: Router, private share: ShareService) {
  }

  ngOnInit(): void {
    this.title.setTitle('Đăng nhập');
    this.islogged = this.token.isLogger();
    // if (this.islogged) {
    //   this.router.navigateByUrl('/')
    // }
  }

  login() {
    this.loginService.login(this.form.value).subscribe(next => {
        if (this.form.controls.rememberMe.value) {
          this.token.rememberMe(next.token, next.id, next.name, next.username, next.phoneNumber, next.email, next.address,
            next.gender, next.dateOfBirth, next.avatar, next.roles, 'local');

        } else {
          this.token.rememberMe(next.token, next.id, next.name, next.username, next.phoneNumber, next.email, next.address,
            next.gender, next.dateOfBirth, next.avatar, next.roles, 'session');
        }
        this.share.sendClickEvent();
        this.router.navigateByUrl('/')
      }, error => {
        this.message = error.error.message
      }
    )
  }
}
