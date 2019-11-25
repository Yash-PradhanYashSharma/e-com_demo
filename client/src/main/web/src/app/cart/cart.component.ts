import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from '@angular/core';
import { Cart } from '../class/Cart';
import { CartAdjustment } from "../class/CartAdjustment";
import { CartHeaderDetail } from "../class/CartHeaderDetail";
import { CartItem } from "../class/CartItem";
import { CartItemDetail } from "../class/CartItemDetail";
import { CartResponse } from '../class/CartResponse';
import { MessageService } from '../message.service';
import { NetworkService } from '../network.service';
import { UserService } from "../user.service";
import { cloneDeep } from 'lodash';

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
    this.cart.cartAdjustments.userId = this.userService.id;
    this.cart.cartAdjustments.freightAdjustment.userId = this.cart.cartAdjustments.taxAdjustment.userId = this.cart.cartAdjustments.promoAdjustment.userId = this.userService.id;
    this.networkService.initializeCart(this.cart).subscribe((response:Cart) => {
      this.cart = response;
      console.log(Object.assign(new Cart(), cloneDeep(response)));
      /*this.cart.cartAdjustments = new TotalAdjustment();*/
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
    this.cart.cartAdjustments.freightAdjustment.amount =34;
    this.cart.cartAdjustments.taxAdjustment.amount =20;
    this.cart.cartItems.forEach(item => {
      /**Should be an array... */
      var cartItemAdjustment = this.cart.cartAdjustments.promoAdjustment;
      cartItemAdjustment.productId = item.productId;
      cartItemAdjustment.amount = item.unitPrice * 0.02;
      this.cart.cartAdjustments.promoAdjustment = cartItemAdjustment;
    });
    /*this.cart.cartAdjustments.forEach(itemAdjustments => {
      promotionTotal += itemAdjustments.amount;
    });*/
    let itemTotalSum = 0;
    this.cart.cartItems.forEach(item => {
      itemTotalSum += item.unitPrice * item.quantity;
    });
    this.cartHeaderDetail.itemTotal = itemTotalSum;
    this.cartHeaderDetail.discountTotal = this.cart.cartAdjustments.freightAdjustment.amount+this.cart.cartAdjustments.promoAdjustment.amount+this.cart.cartAdjustments.taxAdjustment.amount;
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
