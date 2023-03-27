import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StyleService {

  constructor(private httpClient: HttpClient) {
  }

  getAllStyle(): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/style")
  }
}
