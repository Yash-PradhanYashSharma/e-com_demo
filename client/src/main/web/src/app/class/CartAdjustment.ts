export class CartAdjustment {
  productId: string = null;
  adjustmentTypeId: string = null;
  amount: number = 0;

  constructor(adjustmentTypeId: string) {
    this.adjustmentTypeId = adjustmentTypeId;
  }
}
