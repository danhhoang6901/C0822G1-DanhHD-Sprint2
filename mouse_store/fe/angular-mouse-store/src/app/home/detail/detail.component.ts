import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ProductService} from "../../service/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../../model/product";
import {Size} from "../../model/size";
import {SizeService} from "../../service/size.service";
import Swal from "sweetalert2";
import {TokenService} from "../../service/token.service";
import {ShareService} from "../../service/share.service";
import {Image} from "../../model/image";
import {User} from "../../model/user";
import {FormBuilder} from "@angular/forms";
import {BillDetail} from "../../model/bill-detail";
import {CartService} from "../../service/cart.service";
import {LoginService} from "../../service/login.service";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  id: number;
  detailProduct: Product;
  user: User;
  quality = 1;
  quantity = 0;
  cart: BillDetail[];
  totalQuantity = 0;

  sizeList: Size[] = [];
  size: Size = null;
  image: Image[] = [];
  isLogged = false;
  name = "Đăng nhập";
  role = "none";

  constructor(private router: Router, private shareService: ShareService, private token: TokenService,
              private title: Title, private productService: ProductService, private activatedRoute: ActivatedRoute,
              private sizeService: SizeService, private formBuilder: FormBuilder,
              private cartService: CartService,
              private loginService: LoginService) {
    this.activatedRoute.paramMap.subscribe(next => {
      const id = next.get("id")
      if (id != null) {
        this.getProductById(+id);
      }
    });
    this.sizeService.getAllSize().subscribe(next => {
      this.sizeList = next;
    });
  }

  ngOnInit(): void {
    this.title.setTitle("Trang chi tiết");
    this.loader()
    this.isLogged = this.token.isLogger()
    this.shareService.getClickEvent().subscribe(next => {
      this.loader()
    })
    this.getProductById(this.activatedRoute.snapshot.params.id);
    this.getOrder();

  }

  loader() {
    this.isLogged = this.token.isLogger()
    if (this.isLogged) {
      this.loginService.profile1(this.token.getId()).subscribe(next => {
        this.user = next;
        this.name = this.user?.name;
      })
      this.role = this.token.getRole();
      console.log(this.role)
    }
  }

  getProductById(id: any) {
    this.productService.findProductById(id).subscribe(next => {
      this.detailProduct = next;
    })
  }

  addToCart(id: number, quantity: number) {
    if (!this.isLogged) {
      Swal.fire({
        title: "Bạn chưa đăng nhập!",
        icon: "warning",
        buttonsStyling: false,
        confirmButtonText: "Đăng nhập!",
        customClass: {
          confirmButton: "btn btn-primary"
        }
      }).then((result) => {
        if (result.isConfirmed) {
          this.router.navigate(['/login'])
        }
      })
    }
    this.cartService.addBill2(this.user?.id, quantity, this.detailProduct.id).subscribe(next => {
      console.log(this.user.id);
      this.totalQuantity = quantity + this.quantity;
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Đã thêm sản phẩm ' + this.detailProduct.name + ' vào giỏ hàng',
        showConfirmButton: false,
        timer: 2500
      })
    }, error => {
      console.log(error)
    })
  }

  getOrder() {
    this.cartService.getCart(this.user?.id).subscribe(data => {
      this.cart = data;
      for (let i = 0; i < data.length; i++) {
        this.quantity += this.cart[i].quantity;
      }
      console.log('số lượng detail' + this.quantity);
    });
  }

}
