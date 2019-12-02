var Cart = /** @class */ (function () {
    function Cart() {
        this.userId = null;
        this.cartDate = new Date();
        this.itemTotal = 0;
        this.discountTotal = 0;
        this.grandTotal = 0;
        this.items = new Array();
        this.adjustments = new Array();
        this.cartResponse = new CartResponse();
    }
    return Cart;
}());
export { Cart };
export var ADJUSTMENT_TYPES = {
    FREIGHT: 'FREIGHT',
    TAXES: 'TAXES',
    PROMO: 'PROMO'
};
var CartAdjustment = /** @class */ (function () {
    function CartAdjustment(productId, adjustmentTypeId, amount) {
        this.productId = null;
        this.adjustmentTypeId = null;
        this.amount = 0;
        this.productId = productId;
        this.adjustmentTypeId = adjustmentTypeId;
        this.amount = amount;
    }
    return CartAdjustment;
}());
export { CartAdjustment };
var ItemDetail = /** @class */ (function () {
    function ItemDetail() {
        this.productId = null;
        this.price = 0;
        this.quantity = 0;
        this.selectedQuantity = 0;
        this.selectedProductPromoId = null;
        this.productName = null;
        this.productDescription = null;
        this.inventoryItemStatus = null;
        this.productPromos = new Array();
        this.productPrices = new Array();
    }
    return ItemDetail;
}());
export { ItemDetail };
var CartResponse = /** @class */ (function () {
    function CartResponse() {
    }
    return CartResponse;
}());
export { CartResponse };
//# sourceMappingURL=Cart.js.map