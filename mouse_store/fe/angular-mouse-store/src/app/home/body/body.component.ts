import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../service/product.service";
import {Product} from "../../model/product";
import {Title} from "@angular/platform-browser";
import {ShareService} from "../../service/share.service";

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {
  productList: Product[] = [];

  constructor(private productService: ProductService, private title: Title,private share:ShareService) {
    this.loader()
    this.share.getClickEvent().subscribe(
      next => this.loader()
    )
  }

  ngOnInit(): void {
    this.title.setTitle("Trang chủ")
  }
  loader() {
    this.productService.getAllProduct().subscribe(next => {
      this.productList = next;
    })
  }
}
