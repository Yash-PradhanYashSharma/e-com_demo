import {HttpClient} from "@angular/common/http";
import {Component, OnInit} from '@angular/core';
import {ADJUSTMENT_TYPES, Cart, CartAdjustment} from '../class/Cart';
import {MessageService} from '../service/message.service';
import {NetworkService} from '../service/network.service';
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
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
      this.cart.adjustments.push(new CartAdjustment(null, ADJUSTMENT_TYPES.TAXES, 20));
      this.cart.adjustments.push(new CartAdjustment(null, ADJUSTMENT_TYPES.FREIGHT, 80));
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
        cartItemDetail.selectedQuantity = event.target.value;
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
    this.cart.items.forEach(item => {
      if (item.selectedProductPromoId != null) {
        item.productPromos.forEach(promo => {
          this.cart.adjustments = this.cart.adjustments.filter(function (e) {
            return e.productId !== item.productId
          });
          item.productPrices.forEach(price => {
            if (price.productId == item.productId) {
              this.cart.adjustments.push(new CartAdjustment(item.productId, ADJUSTMENT_TYPES.PROMO, price.price));
            }
          });
        });
      }
      this.cart.itemTotal += item.price * item.selectedQuantity;
    });

    this.cart.adjustments.forEach(item => {
      if (item.adjustmentTypeId == ADJUSTMENT_TYPES.FREIGHT || item.adjustmentTypeId == ADJUSTMENT_TYPES.TAXES) {
        shipmentTotal += item.amount;
      }
      if (item.adjustmentTypeId == ADJUSTMENT_TYPES.PROMO) {
        discountTotal += item.amount;
      }
    });
    this.cart.discountTotal = discountTotal;
    this.cart.grandTotal = this.cart.itemTotal - discountTotal + shipmentTotal;
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
