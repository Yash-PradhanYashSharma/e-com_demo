import {CartItem} from "./CartItem";
import {CartAdjustment} from "./CartAdjustment";

export class Cart {
  userId: string = null;
  cartDate: Date = new Date();
  cartItems: CartItem[] = [];
  cartAdjustments: CartAdjustment[] =  [] ;
}
