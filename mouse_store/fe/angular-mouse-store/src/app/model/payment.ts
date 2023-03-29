import {Bill} from "./bill";

export interface Payment {
  id?: number;
  paymentStatus?: number;
  bill?: Bill;
  description?: string;
}
