import { CartItem } from "./CartItem";
import { TotalAdjustment } from "./TotalAdjustment";

export class Cart {
  userId: string = null;
  cartDate: Date = new Date();
  cartAdjustments: TotalAdjustment = new TotalAdjustment();
  cartItems: CartItem[] = [];
}
