export class ProductPrice {
  productId: string;
  productPriceTypeId: string;
  description: string;
  fromDate: Date;
  thruDate: Date;
  price: number;
  productByProductId: string;
  selected:boolean = false;
}
