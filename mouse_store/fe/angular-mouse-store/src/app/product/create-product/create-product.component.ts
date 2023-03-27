import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {Router} from "@angular/router";
import {ProductService} from "../../service/product.service";
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Observable} from "rxjs";
import {Product} from "../../model/product";
import {Category} from "../../model/category";
import {Origin} from "../../model/origin";
import {Style} from "../../model/style";
import {Trademark} from "../../model/trademark";
import {Size} from "../../model/size";
import {CategoryService} from "../../service/category.service";
import {OriginService} from "../../service/origin.service";
import {SizeService} from "../../service/size.service";
import {StyleService} from "../../service/style.service";
import {TrademarkService} from "../../service/trademark.service";
import Swal from "sweetalert2";
import {AngularFireStorage} from "@angular/fire/storage";
import {finalize} from "rxjs/operators";

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
  categoryList: Category[] = [];
  originList: Origin[] = [];
  styleList: Style[] = [];
  trademarkList: Trademark[] = [];

  constructor(private storage: AngularFireStorage, private title: Title, private router: Router, private productService: ProductService,
              private categoryService: CategoryService, private originService: OriginService,
              private styleService: StyleService, private trademarkService: TrademarkService) {
    this.productForm = new FormGroup({
      name: new FormControl(),
      codeProduct: new FormControl(),
      image: new FormControl(),
      color: new FormControl(),
      description: new FormControl(),
      price: new FormControl(),
      material: new FormControl(),
      washingInstructions: new FormControl(),
      category: new FormControl(""),
      origin: new FormControl(""),
      style: new FormControl(""),
      trademark: new FormControl("")
    });
    this.categoryService.getAllCategory().subscribe(next => {
      this.categoryList = next;
    });
    this.originService.getAllOrigin().subscribe(next => {
      this.originList = next;
    });
    this.styleService.getAllStyle().subscribe(next => {
      this.styleList = next;
    });
    this.trademarkService.getAllTrademark().subscribe(next => {
      this.trademarkList = next;
    });
  }

  ngOnInit(): void {
    this.title.setTitle("Thêm mới sản phẩm")
  }

  addProduct() {
    if (this.productForm.valid) {
      this.productService.createProduct(this.productForm.value).subscribe(next => {
        this.router.navigateByUrl("/product")
        Swal.fire({
          position: 'center',
          title: 'Thêm mới thành công',
          icon: 'success',
          showConfirmButton: false,
          timer: 2000
        });
      })
    } else {
      Swal.fire({
        position: 'center',
        icon: 'error',
        title: 'Thêm mới thất bại!',
        text: 'Thêm mới thất bại vui lòng điền đúng tất cả thông tin',
        showConfirmButton: false,
        timer: 2000
      })
    }
  }

  showPreview(event: any) {
    this.selectedImage = event.target.files[0];
    const filePath = this.selectedImage.name;
    const fileRef = this.storage.ref(filePath);
    const task = this.storage.upload(filePath, this.selectedImage);
    task
      .snapshotChanges()
      .pipe(
        finalize(() => {
          this.downloadURL = fileRef.getDownloadURL();
          this.downloadURL.subscribe(url => {
            if (url) {
              // lấy lại url
              this.fb = url;
            }
            this.productForm.patchValue({image: url});
            console.log('link: ', this.fb);
            // console.log('link: ', this.fb);
          });
        })
      )
      .subscribe();
  }

  cancel() {
    Swal.fire({
      title: 'Hủy bỏ',
      html: 'Bạn có muốn hủy bỏ thêm mới thông tin sản phẩm ?',
      icon: 'question',
      showCancelButton: true,
      cancelButtonText: 'Hủy',
      showConfirmButton: true,
      confirmButtonText: 'Có',
      confirmButtonColor: 'red'
    }).then((result) => {
        if (result.isConfirmed) {
          this.router.navigateByUrl("/product");
        }
      }
    );
  }
}
