import {HttpClient} from "@angular/common/http";
import {Component, OnInit} from '@angular/core';
import {ADJUSTMENT_TYPES, Cart, CartAdjustment} from '../class/Cart';
import {MessageService} from '../service/message.service';
import {NetworkService} from '../service/network.service';
import {UserService} from "../service/user.service";
import {Shipment} from "../class/Shipment";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html'
})
export class CartComponent implements OnInit {

  showItem: any[];
  keyword = 'productName';
  cart: Cart;

  constructor(private networkService: NetworkService, private messageService: MessageService, private userService: UserService, private http: HttpClient) {
    messageService.clear();
  }

  ngOnInit() {
    this.cart = new Cart();
    this.cart.userId = this.userService.id;
    this.cart.cartDate = new Date();
    this.networkService.initializeCart(this.cart).subscribe((response) => {
      this.cart.adjustments.push(new CartAdjustment(null, ADJUSTMENT_TYPES.TAXES, 0));
      this.cart.adjustments.push(new CartAdjustment(null, ADJUSTMENT_TYPES.FREIGHT, 0));
    });
  }

  selectEvent(item) {
    this.cart.items.push(item);
    this.calculateAdjustment();
  }

  removeItem(productId) {
    this.cart.items = this.cart.items.filter(item => item.productId !== productId);
    this.cart.adjustments = this.cart.adjustments.filter(function (e) {
      return e.productId !== productId
    });
    this.calculateAdjustment();
  }


  updateQuantity(productId, event) {
    this.cart.items.forEach(cartItemDetail => {
      if (cartItemDetail.productId == productId) {
        cartItemDetail.selectedQuantity = Number(event.target.value);
      }
    });
    this.calculateAdjustment();
  }

  updatePromo(productPromoId, productId) {
    this.cart.items.forEach(cartItemDetail => {
      if (cartItemDetail.productId == productId) {
        cartItemDetail.selectedProductPromoId = productPromoId;
      }
    });
    this.calculateAdjustment();
  }

  onChangeSearch(val: string) {
    this.networkService.productSearch(val).subscribe(response => {
      this.showItem = response;
    }, error => console.log(error));
  }

  calculateAdjustment() {
    this.cart.itemTotal = 0;
    this.cart.discountTotal = 0;
    this.cart.grandTotal = 0;
    let shipmentTotal = 0;
    let discountTotal = 0;
    let widthTotal = 0;
    let depthTotal = 0;
    let heightTotal = 0;
    let weightTotal = 0;
    let taxTotal = 0;
    let itemTotal = 0;
    this.cart.items.forEach(item => {
      if (item.selectedProductPromoId != null) {
        item.productPromos.forEach(promo => {
          this.cart.adjustments = this.cart.adjustments.filter(function (cartAdjustment) {
            return cartAdjustment.productId !== item.productId
          });
          item.productPrices.forEach(price => {
            if (price.productId == item.productId && price.productPriceTypeId == "LIST_PRICE") {
              this.cart.adjustments.push(new CartAdjustment(item.productId, ADJUSTMENT_TYPES.PROMO, price.price));
            }
          });
        });
      }
      taxTotal += item.price * 0.18;
      widthTotal += item.productWidth;
      depthTotal += item.productDepth;
      heightTotal += item.productHeight;
      weightTotal += item.productWeight;
      itemTotal += item.price * item.selectedQuantity;
    });

    this.cart.adjustments.filter(function (cartAdjustment) {
      if (cartAdjustment.adjustmentTypeId == ADJUSTMENT_TYPES.TAXES) {
        cartAdjustment.amount = taxTotal;
      }
    });

    this.networkService.getFreight(new Shipment(widthTotal, depthTotal, heightTotal, weightTotal))
      .subscribe(resp => {
        shipmentTotal = resp.charges;
        this.cart.adjustments.filter(function (cartAdjustment) {
          if (cartAdjustment.adjustmentTypeId == ADJUSTMENT_TYPES.FREIGHT) {
            cartAdjustment.amount = shipmentTotal;
          }
        });
      });

    this.cart.adjustments.forEach(item => {
      if (item.adjustmentTypeId == ADJUSTMENT_TYPES.PROMO) {
        discountTotal += item.amount;
      }
    });

    this.cart.discountTotal = discountTotal;
    this.cart.itemTotal = itemTotal;
    this.cart.grandTotal = itemTotal - discountTotal + shipmentTotal + taxTotal;
  }

  createOrder() {
    this.networkService.createOrder(this.cart).subscribe((res) => {
      this.cart.cartResponse = res;
    });
  }

  onClosed(e) {
  }

  onCleared(e) {
  }

  onFocused(e) {
  }

}
