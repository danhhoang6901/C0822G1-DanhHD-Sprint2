import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ProductService} from "../../service/product.service";
import {ActivatedRoute} from "@angular/router";
import {Product} from "../../model/product";
import {Size} from "../../model/size";
import {SizeService} from "../../service/size.service";
import Swal from "sweetalert2";
import {Cart} from "../../model/cart";
import {TokenService} from "../../service/token.service";
import {ShareService} from "../../service/share.service";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  id;
  productList: Product = {};
  sizeList: Size[] = [];
  carts: Cart[] = [];
  cart: Cart = {};
  size: Size = null;

  constructor(private shareService:ShareService,private token: TokenService, private title: Title, private productService: ProductService, private activatedRoute: ActivatedRoute,
              private sizeService: SizeService) {
    this.activatedRoute.paramMap.subscribe(next => {
      this.id = +next.get("id")
      this.getProductById(this.id);
    });
    this.sizeService.getAllSize().subscribe(next => {
      this.sizeList = next;
    })
    console.log(this.size);
  }

  ngOnInit(): void {
    this.title.setTitle("Trang chi tiết")
  }

  getProductById(id: any) {
    this.productService.findProductById(id).subscribe(next => {
      this.productList = next;
      console.log(this.productList)
    })
  }

  addToCart(ids: number, images: string, names: string, prices: number) {
    console.log(names)
    console.log(ids)
    if (this.token.getCart() != undefined) {
      this.carts = this.token.getCart();
      this.cart.id = ids;
      this.cart.name = names;
      this.cart.image = images;
      this.cart.price = prices;
      if (this.token.checkExist(names)) {
        this.token.upQuantity(ids, this.carts)
      } else {
        this.cart.quantity = 1;
        this.carts.push(this.cart);
      }
      this.token.setCart(this.carts);
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Đã thêm sản phẩm ' + this.cart.name + ' vào giỏ hàng',
        showConfirmButton: false,
        timer: 2500
      })
    } else {
      this.cart.id = ids;
      this.cart.name = names;
      this.cart.image = images;
      this.cart.price = prices;
      this.cart.quantity = 1;
      this.carts.push(this.cart);
      this.token.setCart(this.carts);
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Đã thêm sản phẩm ' + this.cart.name + ' vào giỏ hàng',
        showConfirmButton: false,
        timer: 2500
      })
    }
  }
}
