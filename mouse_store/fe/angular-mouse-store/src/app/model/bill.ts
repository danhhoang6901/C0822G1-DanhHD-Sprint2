import {User} from "./user";

export interface Bill {
  id?: number;
  buyDate?: string;
  detail?: string;
  quantity?: number;
  paymentMethod?: string;
  user?: User;
}
