import {User} from "./user";
import {Payment} from "./payment";
import {BillDetail} from "./bill-detail";

export interface Bill {
  id?: number;
  user?: User;
  payment?: Payment;
  billDetails?: BillDetail;
}
