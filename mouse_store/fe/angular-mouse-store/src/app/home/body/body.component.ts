import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../service/product.service";
import {Product} from "../../model/product";
import {Title} from "@angular/platform-browser";
import {ShareService} from "../../service/share.service";
import {Image} from "../../model/image";
import Swal from "sweetalert2";
import {TokenService} from "../../service/token.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CartService} from "../../service/cart.service";
import {User} from "../../model/user";
import {LoginService} from "../../service/login.service";
import {BillDetail} from "../../model/bill-detail";
import {SizeService} from "../../service/size.service";
import {Size} from "../../model/size";

@Component({
    selector: 'app-body',
    templateUrl: './body.component.html',
    styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {
    productList;
    image: Image[] = []
    search = "";
    role = "none";
    isLogged = false;
    detailProduct: Product;
    user: User;
    totalQuantity = 0;
    quantity = 0;
    name = "Đăng nhập";
    cart: BillDetail[];
    quality = 1;
    sizeProduct: string = 'S'

    constructor(private token: TokenService, private productService: ProductService,
                private title: Title, private share: ShareService, private router: Router,
                private cartService: CartService, private loginService: LoginService,
                private shareService: ShareService) {
        this.loader(this.search)
        this.share.getClickEvent().subscribe(next => {
                this.loader(this.search)
            }
        )
    }

    ngOnInit(): void {
        this.title.setTitle("Trang chủ")
        this.isLogged = this.token.isLogger()
        this.loader1()
        this.shareService.getClickEvent().subscribe(next => {
            this.isLogged = this.token.isLogger()
            this.loader1()
        })
        this.getIdUser();
    }

    getProductById(id: any) {
        this.productService.findProductById(id).subscribe(next => {
            this.detailProduct = next;
        })
    }

    getIdUser() {
        if (this.isLogged) {
            this.loginService.profile1(this.token.getId()).subscribe(next => {
                this.user = next;
            })
        }

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
        } else {
            this.getIdUser();
            this.getProductById(id);
            this.cartService.addBill2(this.user?.id, quantity, id, this.sizeProduct).subscribe(next => {
                this.totalQuantity = quantity + this.quantity;
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Đã thêm sản phẩm ' + this.detailProduct.name + ' vào giỏ hàng',
                    showConfirmButton: false,
                    timer: 2500
                })
                this.shareService.sendClickEvent()
            }, error => {
                console.log(error)
            })
        }

    }

    loader1() {
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

    loader(value: string) {
        this.productService.getAllProduct(value).subscribe(next => {
            if (next != undefined) {
                this.productList = next;
            } else {
                Swal.fire({
                    position: 'center',
                    icon: 'warning',
                    title: 'Không tìm thấy',
                    text: 'Kết quả bạn cần tìm là: ' + '" ' + value + ' " ' + ' không có',
                    showConfirmButton: false,
                    timer: 2000
                });
            }
        })
    }
}
