import {Origin} from "./origin";
import {Category} from "./category";
import {Size} from "./size";
import {Style} from "./style";
import {Trademark} from "./trademark";

export interface Product {
  id?: number;
  codeProduct?: string;
  name?: string;
  image?: string;
  color?: string;
  description?: string;
  quantity?: number;
  price?: number;
  material?: string;
  washingInstructions?: string
  category?: Category;
  origin?: Origin;
  size?: Size;
  style?: Style;
  trademark?: Trademark;
}
