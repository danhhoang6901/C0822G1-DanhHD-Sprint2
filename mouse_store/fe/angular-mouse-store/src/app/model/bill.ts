import {User} from "./user";

export interface Bill {
  id?: number;
  datePurchase?: string;
  totalMoney?: number;
  user?: User;
}
