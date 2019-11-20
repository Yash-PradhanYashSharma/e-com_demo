import {ProductPromo} from "./ProductPromo";
import {ProductPrice} from "./ProductPrice";

export class CartItemDetail {
  productId: string = null;
  quantity: number = 0;
  productName: string = null;
  productDescription: string = null;
  inventoryItemStatus: string = null;
  productPromos: ProductPromo[] = [];
  productPrices: ProductPrice[] = [];
}
