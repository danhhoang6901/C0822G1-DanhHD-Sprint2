import {Component, OnInit} from '@angular/core';
import {BillHistoryService} from "../../service/bill-history.service";
import {BillHistoryDto} from "../../dto/bill-history-dto";
import {BillService} from "../../service/bill.service";
import {Bill} from "../../model/bill";
import {Title} from "@angular/platform-browser";
import {ShareService} from "../../service/share.service";

@Component({
  selector: 'app-bill-history',
  templateUrl: './bill-history.component.html',
  styleUrls: ['./bill-history.component.css']
})
export class BillHistoryComponent implements OnInit {
  billHistory: BillHistoryDto = {};
  bill: Bill = {};

  constructor(private billService: BillService, private billHistoryService: BillHistoryService,
              private title: Title, private shareService: ShareService) {
  }

  ngOnInit(): void {
    this.title.setTitle("Lịch sử mua hàng")
    this.showAllBill();
    this.shareService.getClickEvent().subscribe(next =>{
      this.showAllBill();
      this.getBillById();
    })
  }

  showBillDetail() {
    this.billHistoryService.showBillDetail(this.bill.id).subscribe(next => {
      this.billHistory = next;
    })
  }

  showAllBill() {
    this.billService.showAllBill().subscribe(next => {
      this.bill = next;
      this.getBillById();
    })
  }

  getBillById() {
    this.billService.findBillById(this.bill.id).subscribe(next => {
      this.bill = next;
      this.showBillDetail();
    })
  }
}
