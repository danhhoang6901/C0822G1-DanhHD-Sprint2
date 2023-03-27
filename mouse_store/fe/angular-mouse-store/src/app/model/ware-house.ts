import {Product} from "./product";
import {Size} from "./size";

export interface WareHouse {
  id?: number;
  dateImports?: string;
  quantity?: number;
  product?: Product;
  size?: Size;
}
