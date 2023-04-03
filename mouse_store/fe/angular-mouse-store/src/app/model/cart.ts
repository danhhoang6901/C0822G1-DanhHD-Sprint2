import {Size} from "./size";
import {Product} from "./product";
import {User} from "./user";

export interface Cart {
  id?: number;
  quantity?: number;
  size?: string;
  product?: Product;
  user?: User;
}
