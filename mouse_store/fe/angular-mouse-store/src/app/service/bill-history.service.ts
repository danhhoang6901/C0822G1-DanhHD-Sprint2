import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BillHistoryService {

  constructor(private httpClient: HttpClient) { }

  showBillDetail(id: number): Observable<any>{
    return this.httpClient.get<any>("http://localhost:8080/billHistory/" + id);
  }
}
