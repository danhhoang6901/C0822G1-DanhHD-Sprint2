import {Product} from "./product";
import {Bill} from "./bill";

export interface BillDetail {
  id?: number;
  quantity?: number;
  product?: Product;
  bill?: Bill;
}
