import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SizeService {

  constructor(private httpClient: HttpClient) { }

  getAllSize(): Observable<any>{
    return this.httpClient.get<any>("http://localhost:8080/size")
  }
}
