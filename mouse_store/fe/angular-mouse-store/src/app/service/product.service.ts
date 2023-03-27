import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) {
  }

  getAllProduct(): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/products")
  }

  findProductById(id: number): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/products/" + id);
  }

  showListProduct(search: string, page: number): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/products/list?search=" + search + "&page=" + page);
  }

  createProduct(product): Observable<any> {
    return this.httpClient.post("http://localhost:8080/products/create", product);
  }
}
