import {Category} from "../model/category";
import {Origin} from "../model/origin";
import {Style} from "../model/style";
import {Trademark} from "../model/trademark";
import {ImageDto} from "./image-dto";

export interface ProductDto {
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
  image?: ImageDto;
}
