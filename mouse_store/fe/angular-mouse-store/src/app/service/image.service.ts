import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private httpClient: HttpClient) {
  }

  showImageByIdProduct(id): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/image/" + id);
  }

  deleteImageById(id): Observable<any>{
    return this.httpClient.delete<any>("http://localhost:8080/image/" + id)
  }
}
