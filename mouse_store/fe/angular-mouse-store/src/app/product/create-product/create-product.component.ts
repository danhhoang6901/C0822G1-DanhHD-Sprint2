import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {Router} from "@angular/router";
import {ProductService} from "../../service/product.service";
import {FormGroup} from "@angular/forms";
import {Observable} from "rxjs";
import {Product} from "../../model/product";

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {
  productForm: FormGroup;
  selectedImage: any = null;
  downloadURL: Observable<string> | undefined;
  fb: string | undefined;
  src: string | undefined;
  productList: Product[] = [];

  constructor(private title: Title, private router: Router, private productService: ProductService) {
  }

  ngOnInit(): void {
    this.title.setTitle("Thêm mới sản phẩm")
  }

}
