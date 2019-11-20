import {Component, OnInit} from '@angular/core';
import {NetworkService} from '../network.service';
import {Cart} from '../class/Cart';
import {MessageService} from '../message.service';
import {CartResponse} from '../class/CartResponse';
import {UserService} from "../user.service";
import {HttpClient} from "@angular/common/http";
import {CartItem} from "../class/CartItem";
import {CartItemDetail} from "../class/CartItemDetail";
import {CartAdjustment} from "../class/CartAdjustment";
import {CartHeaderDetail} from "../class/CartHeaderDetail";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  private showItem: any[];
  private keyword = 'productName';
  private cart: Cart;
  private cartResponse: CartResponse;
  private cartItemsDetail: CartItemDetail[] = new Array<CartItemDetail>();
  private cartHeaderDetail = new CartHeaderDetail();

  constructor(private networkService: NetworkService, private messageService: MessageService, private userService: UserService, private http: HttpClient) {
    messageService.clear();
  }

  ngOnInit() {
    this.cart = new Cart();
    this.cart.userId = this.userService.id;
    this.cart.cartDate = new Date();
    this.cartHeaderDetail.itemTotal = 0;
    this.cartHeaderDetail.discountTotal = 0;
    this.cartHeaderDetail.grandTotal = 0;
    this.cart.cartAdjustments = [new CartAdjustment('FREIGHT'), new CartAdjustment('TAXES')];
    this.networkService.initializeCart(this.cart).subscribe((response) => {
      this.cart = response;
    });
  }

  selectEvent(item) {
    const cartItem = new CartItem();
    cartItem.productId = item.productId;
    cartItem.quantity = item.quantity;
    item.productPrices.forEach(prices => {
      if (prices.productPriceTypeId == 'LIST_PRICE') {
        cartItem.unitPrice = prices.price;
      }
    });
    this.cart.cartItems.push(cartItem);
    this.cartItemsDetail.push(item);
    this.refresh();
  }

  removeItem(productId) {
    this.cart.cartItems = this.cart.cartItems.filter(item => item.productId !== productId);
    this.cartItemsDetail = this.cartItemsDetail.filter(item => item.productId !== productId);
    this.refresh();
  }

  onChangeSearch(val: string) {
    this.networkService.productSearch(val).subscribe(response => {
      this.showItem = response;
    }, error => console.log(error));
  }

  refresh() {
    this.calculateAdjustment();
  }

  private calculateAdjustment() {
    this.cart.cartAdjustments.filter(cartAdjustment => {
      if (cartAdjustment.adjustmentTypeId == 'FREIGHT') {
        cartAdjustment.amount = 34;
      }
      if (cartAdjustment.adjustmentTypeId == 'TAXES') {
        cartAdjustment.amount = 20;
      }
    });
    this.cart.cartItems.forEach(item => {
      var cartItemAdjustment = new CartAdjustment('PROMO');
      cartItemAdjustment.productId = item.productId;
      cartItemAdjustment.amount = item.unitPrice * 0.02;
      this.cart.cartAdjustments.push(cartItemAdjustment);
    });


    let promotionTotal = 0;
    this.cart.cartAdjustments.forEach(itemAdjustments => {
      promotionTotal += itemAdjustments.amount;
    });
    let itemTotalSum = 0;
    this.cart.cartItems.forEach(item => {
      itemTotalSum += item.unitPrice * item.quantity;
    });
    this.cartHeaderDetail.itemTotal = itemTotalSum;
    this.cartHeaderDetail.discountTotal = promotionTotal;
    this.cartHeaderDetail.grandTotal = this.cartHeaderDetail.itemTotal - this.cartHeaderDetail.discountTotal;
    console.log('-----', this.cart.cartAdjustments);
  }

  createOrder() {
    this.networkService.createOrder(this.cart).subscribe((res) => {
      this.cartResponse = res;
    });
  }

  onClosed(e) {
  }

  onCleared(e) {
  }

  onFocused(e) {
  }

}
