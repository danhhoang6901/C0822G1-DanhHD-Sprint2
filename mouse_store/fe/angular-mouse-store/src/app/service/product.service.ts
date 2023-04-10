import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) {
  }

  getAllProduct(name, page: number): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/products?name=" + name + "&page=" + page);
  }

  findProductById(id): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/products/find/" + id);
  }

  showListProduct(search: string, page: number): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/products/list?search=" + search + "&page=" + page);
  }

  createProduct(product): Observable<any> {
    return this.httpClient.post("http://localhost:8080/products/create", product);
  }

  createImage(image): Observable<any> {
    return this.httpClient.post("http://localhost:8080/products/create/img", image);
  }

  deleteProduct(id: number): Observable<any> {
    return this.httpClient.delete<any>("http://localhost:8080/products/delete/" + id);
  }

  editProduct(id, product): Observable<any> {
    return this.httpClient.put<any>("http://localhost:8080/products/edit/" + id, product);
  }
}
