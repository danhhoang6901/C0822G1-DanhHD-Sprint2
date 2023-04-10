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
import {Image} from "../../model/image";
import {ImageDto} from "../../dto/image-dto";
import {ProductDto} from "../../dto/product-dto";
import {ShareService} from "../../service/share.service";

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {
  productForm: FormGroup;
  selectedImage: any[] = [];
  downloadURL: Observable<string> | undefined;
  fb: string | undefined;
  src: string | undefined;
  categoryList: Category[] = [];
  originList: Origin[] = [];
  styleList: Style[] = [];
  trademarkList: Trademark[] = [];
  img: any[] = [];
  readFile: any[] | [];
  product: ProductDto;

  constructor(private formBuilder: FormBuilder, private storage: AngularFireStorage, private title: Title,
              private router: Router, private productService: ProductService,
              private categoryService: CategoryService, private originService: OriginService,
              private styleService: StyleService, private trademarkService: TrademarkService,
              private shareService: ShareService) {
    this.productForm = this.formBuilder.group({
      name: new FormControl(),
      codeProduct: new FormControl(),
      image: [],
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
    console.log(this.readFile)
    if (this.productForm.valid) {
      this.product = this.productForm.value;
      for (let i = 0; i < this.readFile.length; i++) {
        this.selectedImage = this.readFile[i];
        const filePath = "Image Product";
        const fileRef = this.storage.ref(filePath);
        const task = this.storage.upload(filePath, this.selectedImage);
        task
          .snapshotChanges()
          .pipe(
            finalize(() => {
              this.downloadURL = fileRef.getDownloadURL();
              this.downloadURL.subscribe(url => {
                this.img.push(url);
                // console.log('link: ', this.fb);
              });
            })
          )
          .subscribe(() => {
          });
      }
      setTimeout(() => {
        this.productService.createProduct(this.product).subscribe(next => {
          console.log(this.img.length)

          if (this.img.length != 0) {
            for (let i = 0; i < this.img.length; i++) {
              const image: ImageDto = {
                url: this.img[i],
                product: next.id
              };
              this.productService.createImage(image).subscribe(next => {
              })
            }
          }
          Swal.fire({
            position: 'center',
            title: 'Thêm mới thành công',
            icon: 'success',
            showConfirmButton: false,
            timer: 2000
          });
          this.router.navigateByUrl("/product")
        })
      }, 10000)
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
    const files = event.target.files;
    this.readFile = event.target.files;
    if ((files.length + this.selectedImage.length) < 6) {
      for (const file of files) {
        if (file.size > 1048576) {
          this.productForm.patchValue({image: []});
          this.selectedImage = [];
          break;
        }
        const reader = new FileReader();
        reader.onload = (e: any) => {
          this.selectedImage.push(e.target.result);
          console.log('size' + this.selectedImage);
        };
        reader.readAsDataURL(file);
        console.log('sizezzz' + file);
      }
    } else {
      this.productForm.patchValue({image: []});
      this.selectedImage = [];
    }
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

  deleteImage(i: number) {
    if (this.selectedImage.length == 1) {
      this.selectedImage.splice(i, 1);
      this.productForm.controls.images.setValue([]);
    } else {
      this.selectedImage.splice(i, 1);
    }
  }
}
