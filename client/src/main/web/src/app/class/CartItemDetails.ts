import {ProductPromo} from "./ProductPromo";
import {ProductPrice} from "./ProductPrice";

export class CartItemDetails {
  userId: string;
  productId: string;
  quantity: number;
  unitPrice: number;
  productName: string;
  productDescription: string;
  productPromos: ProductPromo[];
  productPrices: ProductPrice[];
  inventoryItemStatus: string;
}
