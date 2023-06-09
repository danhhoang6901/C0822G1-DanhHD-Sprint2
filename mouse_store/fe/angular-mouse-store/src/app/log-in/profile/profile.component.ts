import {Component, OnInit} from '@angular/core';
import Swal from "sweetalert2";
import {User} from "../../model/user";
import {FormControl, FormGroup} from "@angular/forms";
import {Observable} from "rxjs";
import {AngularFireStorage} from "@angular/fire/storage";
import {ShareService} from "../../service/share.service";
import {TokenService} from "../../service/token.service";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../../service/login.service";
import {Title} from "@angular/platform-browser";
import {finalize} from "rxjs/operators";
import {Bill} from "../../model/bill";
import {BillService} from "../../service/bill.service";
import {BillHistoryService} from "../../service/bill-history.service";
import {BillHistoryDto} from "../../dto/bill-history-dto";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  index = 0;
  idLoadding = false;
  nameError = '';
  phoneNumberError = '';
  emailError = '';
  addressError = '';
  genderError = '';
  dateOfBirthError = '';
  avatarError = '';
  user: User;
  form = new FormGroup({
    name: new FormControl(''),
    phoneNumber: new FormControl(''),
    email: new FormControl(''),
    address: new FormControl(''),
    gender: new FormControl(''),
    dateOfBirth: new FormControl(''),
    avatar: new FormControl('')
  })
  role = '';
  formPassword = new FormGroup({
    password: new FormControl(''),
    newPassword: new FormControl(''),
    confirmPassword: new FormControl('')
  })
  downloadURL: Observable<string> | undefined;
  src: string | undefined;
  bill: Bill = {};
  billHistory: BillHistoryDto[];
  isLogged = false;

  constructor(private storage: AngularFireStorage, private share: ShareService, private token: TokenService,
              private router: Router, private userService: LoginService, private title: Title,
              private billService: BillService, private loginService: LoginService,
              private billHistoryService: BillHistoryService) {
  }

  ngOnInit(): void {
    this.isLogged = this.token.isLogger()
    this.loader()
    this.share.getClickEvent().subscribe(next => {
      this.isLogged = this.token.isLogger()
      this.loader()
    })
    console.log(this.role)
    console.log(this.index)
    this.title.setTitle('Trang cá nhân');
    // if (!this.token.isLogger()) {
    //   this.router.navigateByUrl('/home')
    // } else {
    //   this.getValue();
    // }
    this.getIdUser();
  }

  loader() {
    this.isLogged = this.token.isLogger()
    if (this.isLogged) {
      this.loginService.profile1(this.token.getId()).subscribe(next => {
        this.user = next;
        this.getValue()
      })
      this.role = this.token.getRole();
    }
  }

  getValue() {
    this.form.controls.name.patchValue(this.user?.name);
    this.form.controls.phoneNumber.patchValue(this.user?.phoneNumber);
    this.form.controls.email.patchValue(this.user?.email);
    this.form.controls.gender.patchValue(this.user?.gender);
    this.form.controls.address.patchValue(this.user?.address);
    this.form.controls.dateOfBirth.patchValue(this.user?.dateOfBirth);
    this.form.controls.avatar.patchValue(this.user?.avatar);
  }

  getIdUser() {
    this.loginService.profile1(this.token.getId()).subscribe(next => {
      this.user = next;
      this.showBill(0);
    })
  }

  showBill(page: number) {
    this.billService.showBill(page, this.user?.id).subscribe(next => {
      this.bill = next;
    })
  }


  getForm() {
    this.formPassword = new FormGroup({
      password: new FormControl(''),
      newPassword: new FormControl(''),
      confirmPassword: new FormControl('')
    })
  }

  update() {
    this.nameError = '';
    this.phoneNumberError = '';
    this.emailError = '';
    this.addressError = '';
    this.genderError = '';
    this.dateOfBirthError = '';
    this.avatarError = '';
    this.userService.updateUser(this.form.value).subscribe(next => {
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Chúc mừng ' + this.form.controls.name.value + ' đã cập nhật thông tin thành công',
        showConfirmButton: false,
        timer: 2500
      })
      document.getElementById('huy').click()
      this.share.sendClickEvent();

    }, error => {
      Swal.fire({
        position: 'center',
        icon: 'error',
        title: 'Vui lòng điền đầy đủ thông tin vào ông trống',
        showConfirmButton: false,
        timer: 2500
      })
      for (let i = 0; i < error.error.length; i++) {
        if (error.error[i].field == 'name') {
          this.nameError = error.error[i].defaultMessage;
        } else if (error.error[i].field == 'phoneNumber') {
          this.phoneNumberError = error.error[i].defaultMessage;
        } else if (error.error[i].field == 'email') {
          this.emailError = error.error[i].defaultMessage;
        } else if (error.error[i].field == 'address') {
          this.addressError = error.error[i].defaultMessage;
        } else if (error.error[i].field == 'dateOfBirth') {
          this.dateOfBirthError = error.error[i].defaultMessage;
        } else if (error.error[i].field == 'avatar') {
          this.avatarError = error.error[i].defaultMessage;
        }
      }
    })

  }

  selectedImage: any = null;

  showPreview(event: any) {
    this.selectedImage = event.target.files[0];
    const filePath = this.selectedImage.name;
    const fileRef = this.storage.ref(filePath);
    const task = this.storage.upload(filePath, this.selectedImage);
    this.idLoadding = true
    task
      .snapshotChanges()
      .pipe(
        finalize(() => {
          this.downloadURL = fileRef.getDownloadURL();
          this.downloadURL.subscribe(url => {
            if (url) {
              this.idLoadding = true;
              // lấy lại url
            }
            this.form.patchValue({avatar: url});
            this.idLoadding = false;
            // console.log('link: ', this.fb);
          });
        })
      )
      .subscribe();
  }

  passwordError = '';
  newPasswordError = '';
  confirmPasswordError = '';
  op: boolean;
  np: boolean;
  cp: boolean;

  changePassword() {
    this.passwordError = '';
    this.newPasswordError = '';
    this.confirmPasswordError = '';
    this.userService.changePassword(this.formPassword.value).subscribe(
      next => {
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Chúc mừng ' + this.user.name + ' đã cập nhật mật khẩu thành công',
          showConfirmButton: false,
          timer: 2500
        })
        this.getForm();
        document.getElementById('dismiss2').click()
      }, error => {
        console.log(error)
        Swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Thay đổi mật khẩu thất bại',
          showConfirmButton: false,
          timer: 2500
        })
        for (let i = 0; i < error.error.length; i++) {
          if (error.error[i].field == 'password') {
            this.passwordError = error.error[i].defaultMessage;
          } else if (error.error[i].field == 'newPassword') {
            this.newPasswordError = error.error[i].defaultMessage;
          } else if (error.error[i].field == 'confirmPassword') {
            this.confirmPasswordError = error.error[i].defaultMessage;
          }
        }
      }
    )
  }

  oldPassword() {
    this.op = !this.op;

  }

  newPassword() {
    this.np = !this.np;

  }

  confirmPassword() {
    this.cp = !this.cp;

  }

  showBillDetail(id) {
    this.index = 1
    console.log(id)
    this.billHistoryService.showBillDetail(id).subscribe(next => {
      this.billHistory = next;
      console.log(next)
    })
  }
}
