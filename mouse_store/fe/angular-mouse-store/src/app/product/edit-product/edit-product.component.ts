import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Category} from "../../model/category";
import {Origin} from "../../model/origin";
import {Style} from "../../model/style";
import {Trademark} from "../../model/trademark";
import {AngularFireStorage} from "@angular/fire/storage";
import {Title} from "@angular/platform-browser";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {ProductService} from "../../service/product.service";
import {CategoryService} from "../../service/category.service";
import {OriginService} from "../../service/origin.service";
import {StyleService} from "../../service/style.service";
import {TrademarkService} from "../../service/trademark.service";
import {Product} from "../../model/product";
import Swal from "sweetalert2";
import {Observable} from "rxjs";
import {ProductDto} from "../../dto/product-dto";
import {finalize} from "rxjs/operators";
import {ImageDto} from "../../dto/image-dto";
import {ShareService} from "../../service/share.service";
import {ImageService} from "../../service/image.service";

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  productForm: FormGroup;
  categoryList: Category[] = [];
  originList: Origin[] = [];
  styleList: Style[] = [];
  trademarkList: Trademark[] = [];
  productList: Product;
  selectedImage: any[] = [];
  downloadURL: Observable<string> | undefined;
  fb: string | undefined;
  src: string | undefined;
  img: any[] = [];
  readFile: any[] = [];
  product: ProductDto;
  id: number | undefined;
  image: ImageDto[] = [];
  idImageList: any[] = [];

  constructor(private formBuilder: FormBuilder, private storage: AngularFireStorage, private title: Title,
              private router: Router, private productService: ProductService,
              private categoryService: CategoryService, private originService: OriginService,
              private styleService: StyleService, private trademarkService: TrademarkService,
              private activatedRoute: ActivatedRoute, private imageService: ImageService) {

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
    this.title.setTitle("Chỉnh sửa sản phẩm")
    // this.getProductById()
  }

  // getProductById() {
  //   this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
  //     // @ts-ignore
  //     this.id = +paramMap.get("id");
  //     console.log('id nè ' + this.id);
  //     this.productService.findProductById(this.id).subscribe(anime => {
  //       this.anime = anime;
  //       this.animeService.getListImgProductId(this.anime.id).subscribe(value => {
  //         this.imgs = value;
  //       });
  //       this.formEditAnime = this.formBuilder.group({
  //         id: [anime.id],
  //         name: [anime.name],
  //         description: [anime.description],
  //         price: [anime.price],
  //         quantity: [anime.quantity],
  //         author: [anime.author],
  //         origin: [anime.origin],
  //         images: [1],
  //       });
  //     }, error => {
  //     });
  //   });
  // }

  compareFun(item1, item2) {
    return item1 && item2 ? item1.id === item2.id : item1 === item2;
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

  editProduct(id: any) {
    if (this.productForm.valid) {
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
        this.product = this.productForm.value;
        this.productService.editProduct(this.product, id).subscribe(next => {
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
          if (this.idImageList.length !== 0) {
            for (let i = 0; i < this.idImageList.length; i++) {
              this.imageService.deleteImageById(this.idImageList[i]).subscribe(next => {
              })
            }
          }
          Swal.fire({
            position: 'center',
            title: 'Chỉnh sửa thành công',
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
        title: 'Chỉnh sửa thất bại!',
        text: 'Chỉnh sửa thất bại vui lòng điền đúng tất cả thông tin',
        showConfirmButton: false,
        timer: 2000
      })
    }
  }

  showPreview(event: any) {
    const files = event.target.files;
    this.readFile = event.target.files;
    console.log(this.readFile)
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
        };
        reader.readAsDataURL(file);
      }
    } else {
      this.productForm.patchValue({image: []});
      this.selectedImage = [];
    }
  }

  deleteImageNew(i: number) {
    if (this.selectedImage.length == 1) {
      this.selectedImage.splice(i, 1);
      this.productForm.controls.image.setValue([]);
    } else {
      this.selectedImage.splice(i, 1);
    }
    console.log(this.productForm.value)
  }

  deleteImage(i: number, img: any) {
    this.idImageList.push(img.id);
    this.image.splice(i, 1);
  }
}
