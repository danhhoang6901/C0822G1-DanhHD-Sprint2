import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BillService {

  constructor(private httpClient: HttpClient) {
  }

  showBill(page: number, id: number): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/bill/" + id + "?page=" + page);
  }

  findBillById(id): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/bill/find/" + id);
  }

  showAllBill(): Observable<any> {
    return this.httpClient.get<any>("http://localhost:8080/bill")
  }
}
