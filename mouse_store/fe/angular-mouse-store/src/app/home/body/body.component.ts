import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../service/product.service";
import {Product} from "../../model/product";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {
  productList: Product[] = [];

  constructor(private productService: ProductService, private title: Title) {
    this.productService.getAllProduct().subscribe(next => {
      this.productList = next;
    })
  }

  ngOnInit(): void {
    this.title.setTitle("Trang chủ")
  }

}
