import {User} from "./user";
import {BillDetail} from "./bill-detail";

export interface Bill {
  id?: number;
  user?: User;
  billDetails?: BillDetail;
}
