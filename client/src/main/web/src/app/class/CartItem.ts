import {ProductPromo} from "./ProductPromo";
import {ProductPrice} from "./ProductPrice";

export class CartItem {
  userId: string;
  productId: string;
  productName: string;
  productDescription: string;
  productPromos: ProductPromo[];
  productPrices: ProductPrice[];
  quantity: number;
  inventoryItemStatus: string;
}
