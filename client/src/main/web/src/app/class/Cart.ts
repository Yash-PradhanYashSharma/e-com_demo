import {ProductPromo} from "./ProductPromo";
import {ProductPrice} from "./ProductPrice";

export class Cart {
  userId: string = null;
  cartDate: Date = new Date();
  itemTotal: number = 0;
  discountTotal: number = 0;
  grandTotal: number = 0;
  items: ItemDetail[] = new Array<ItemDetail>();
  adjustments: CartAdjustment[] = new Array<CartAdjustment>();
  cartResponse: CartResponse = new CartResponse();
}

export const ADJUSTMENT_TYPES =
  {
    FREIGHT: 'FREIGHT',
    TAXES: 'TAXES',
    PROMO: 'PROMO'
  };

export class CartAdjustment {
  productId: string = null;
  adjustmentTypeId: string = null;
  amount: number = 0;

  constructor(productId: string, adjustmentTypeId: string, amount: number) {
    this.productId = productId;
    this.adjustmentTypeId = adjustmentTypeId;
    this.amount = amount;
  }
}

export class ItemDetail {
  productId: string = null;
  price: number = 0;
  quantity: number = 0;
  selectedQuantity: number = 0;
  selectedProductPromoId: string = null;
  productName: string = null;
  productDescription: string = null;
  inventoryItemStatus: string = null;
  productPromos: ProductPromo[] = new Array<ProductPromo>();
  productPrices: ProductPrice[] = new Array<ProductPrice>();
}

export class CartResponse {
  orderId: string;
  message: string;
}
