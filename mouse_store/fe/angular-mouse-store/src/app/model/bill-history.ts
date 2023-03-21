import {Bill} from "./bill";
import {Product} from "./product";

export interface BillHistory {
  id?: number;
  bill?: Bill;
  product?: Product;
}
