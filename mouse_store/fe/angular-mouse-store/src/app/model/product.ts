import {Origin} from "./origin";
import {Category} from "./category";
import {Style} from "./style";
import {Trademark} from "./trademark";
import {Image} from "./image";

export interface Product {
  id?: number;
  codeProduct?: string;
  name?: string;
  color?: string;
  description?: string;
  quantity?: number;
  price?: number;
  material?: string;
  washingInstructions?: string
  category?: Category;
  origin?: Origin;
  style?: Style;
  trademark?: Trademark;
  images?: Image[];
}
