import { __decorate, __metadata } from "tslib";
import { HttpClient } from "@angular/common/http";
import { Component } from '@angular/core';
import { ADJUSTMENT_TYPES, Cart, CartAdjustment } from '../class/Cart';
import { MessageService } from '../service/message.service';
import { NetworkService } from '../service/network.service';
import { UserService } from "../service/user.service";
var CartComponent = /** @class */ (function () {
    function CartComponent(networkService, messageService, userService, http) {
        this.networkService = networkService;
        this.messageService = messageService;
        this.userService = userService;
        this.http = http;
        this.keyword = 'productName';
        messageService.clear();
    }
    CartComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.cart = new Cart();
        this.cart.userId = this.userService.id;
        this.cart.cartDate = new Date();
        this.networkService.initializeCart(this.cart).subscribe(function (response) {
            _this.cart.adjustments.push(new CartAdjustment(null, ADJUSTMENT_TYPES.TAXES, 20));
            _this.cart.adjustments.push(new CartAdjustment(null, ADJUSTMENT_TYPES.FREIGHT, 80));
        });
    };
    CartComponent.prototype.selectEvent = function (item) {
        this.cart.items.push(item);
        this.calculateAdjustment();
    };
    CartComponent.prototype.removeItem = function (productId) {
        this.cart.items = this.cart.items.filter(function (item) { return item.productId !== productId; });
        this.cart.adjustments = this.cart.adjustments.filter(function (e) {
            return e.productId !== productId;
        });
        this.calculateAdjustment();
    };
    CartComponent.prototype.updateQuantity = function (productId, event) {
        this.cart.items.forEach(function (cartItemDetail) {
            if (cartItemDetail.productId == productId) {
                cartItemDetail.selectedQuantity = event.target.value;
            }
        });
        this.calculateAdjustment();
    };
    CartComponent.prototype.updatePromo = function (productPromoId, productId) {
        this.cart.items.forEach(function (cartItemDetail) {
            if (cartItemDetail.productId == productId) {
                cartItemDetail.selectedProductPromoId = productPromoId;
            }
        });
        this.calculateAdjustment();
    };
    CartComponent.prototype.onChangeSearch = function (val) {
        var _this = this;
        this.networkService.productSearch(val).subscribe(function (response) {
            _this.showItem = response;
        }, function (error) { return console.log(error); });
    };
    CartComponent.prototype.calculateAdjustment = function () {
        var _this = this;
        this.cart.itemTotal = 0;
        this.cart.discountTotal = 0;
        this.cart.grandTotal = 0;
        var shipmentTotal = 0;
        var discountTotal = 0;
        this.cart.items.forEach(function (item) {
            if (item.selectedProductPromoId != null) {
                item.productPromos.forEach(function (promo) {
                    _this.cart.adjustments = _this.cart.adjustments.filter(function (e) {
                        return e.productId !== item.productId;
                    });
                    item.productPrices.forEach(function (price) {
                        if (price.productId == item.productId) {
                            _this.cart.adjustments.push(new CartAdjustment(item.productId, ADJUSTMENT_TYPES.PROMO, price.price));
                        }
                    });
                });
            }
            _this.cart.itemTotal += item.price * item.selectedQuantity;
        });
        this.cart.adjustments.forEach(function (item) {
            if (item.adjustmentTypeId == ADJUSTMENT_TYPES.FREIGHT || item.adjustmentTypeId == ADJUSTMENT_TYPES.TAXES) {
                shipmentTotal += item.amount;
            }
            if (item.adjustmentTypeId == ADJUSTMENT_TYPES.PROMO) {
                discountTotal += item.amount;
            }
        });
        this.cart.discountTotal = discountTotal;
        this.cart.grandTotal = this.cart.itemTotal - discountTotal + shipmentTotal;
    };
    CartComponent.prototype.createOrder = function () {
        var _this = this;
        this.networkService.createOrder(this.cart).subscribe(function (res) {
            _this.cart.cartResponse = res;
        });
    };
    CartComponent.prototype.onClosed = function (e) {
    };
    CartComponent.prototype.onCleared = function (e) {
    };
    CartComponent.prototype.onFocused = function (e) {
    };
    CartComponent = __decorate([
        Component({
            selector: 'app-cart',
            templateUrl: './cart.component.html',
            styleUrls: ['./cart.component.css']
        }),
        __metadata("design:paramtypes", [NetworkService, MessageService, UserService, HttpClient])
    ], CartComponent);
    return CartComponent;
}());
export { CartComponent };
//# sourceMappingURL=cart.component.js.map