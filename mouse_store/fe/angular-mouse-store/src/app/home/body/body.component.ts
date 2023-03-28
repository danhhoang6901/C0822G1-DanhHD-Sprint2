import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../service/product.service";
import {Product} from "../../model/product";
import {Title} from "@angular/platform-browser";
import {ShareService} from "../../service/share.service";
import {Image} from "../../model/image";
import Swal from "sweetalert2";
import {TokenService} from "../../service/token.service";

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

  constructor(private token: TokenService, private productService: ProductService, private title: Title, private share: ShareService) {
    this.loader(this.search)
    this.share.getClickEvent().subscribe(
      next => this.loader(this.search)
    )
  }

  ngOnInit(): void {
    this.title.setTitle("Trang chủ")
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
