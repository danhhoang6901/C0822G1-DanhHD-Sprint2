import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ProductService} from "../../service/product.service";
import {TokenService} from "../../service/token.service";
import {Router} from "@angular/router";
import {Product} from "../../model/product";
import Swal from "sweetalert2";

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {
  productList;
  search = "";
  product: Product = null;
  nums;
  role = "none";

  constructor(private token: TokenService, private router: Router, private title: Title, private productService: ProductService) {
  }

  ngOnInit(): void {
    this.title.setTitle("Quản lý sản phẩm")
    this.showListProduct(0);
  }

  showListProduct(page: number) {
    this.role = this.token.getRole();
    this.productService.showListProduct(this.search, page).subscribe(next => {
      if (next['content'].length == 0) {
        Swal.fire({
          position: 'center',
          icon: 'warning',
          title: 'Không tìm thấy',
          text: 'Kết quả bạn cần tìm là: ' + '" ' + this.search + ' " ' + ' không có',
          showConfirmButton: false,
          timer: 2000
        })
      } else {
        this.productList = next;
        console.log(this.productList)
        this.nums = Array.from(Array(next.totalPages).keys())
      }
    })
  }
}
