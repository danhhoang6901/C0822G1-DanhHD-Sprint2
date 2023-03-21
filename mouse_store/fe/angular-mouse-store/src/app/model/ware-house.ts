import {Product} from "./product";

export interface WareHouse {
  id?: number;
  dateImports?: string;
  quantity?: number;
  product?: Product;
}
