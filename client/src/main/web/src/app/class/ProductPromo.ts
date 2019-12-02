export class ProductPromo {
  productPromoId: string;
  promoName: string;
  productPromoCode: string;
  useLimitPerCode: string;
  useLimitPerCustomer: string;
  fromDate: Date;
  thruDate: Date;
  productPromoCond: string;
  productPromoAction: string;
  selected: boolean = false;
}

