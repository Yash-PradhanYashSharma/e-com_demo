import {Component, OnInit} from '@angular/core';
import {NetworkService} from '../network.service';
import {Cart} from '../class/Cart';
import {MessageService} from '../message.service';
import {CartResponse} from '../class/CartResponse';
import {UserService} from "../user.service";
import {HttpClient} from "@angular/common/http";
import {CartItem} from "../class/CartItem";
import {CartItemDetails} from "../class/CartItemDetails";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  private cart: Cart = new Cart();
  private data: CartItemDetails;
  private cartResponse: CartResponse;
  private keyword = 'productName';

  constructor(private networkService: NetworkService, private messageService: MessageService, private userService: UserService, private http: HttpClient) {
    messageService.clear();
  }

  ngOnInit() {
    this.cart.userId = this.userService.id;
    this.cart.cartDate = new Date();
    this.networkService.initializeCart(this.cart).subscribe((response) => {
      this.cart = response;
    });
  }

  createOrder() {
    this.networkService.createOrder(this.cart).subscribe((res) => {
      this.cartResponse = res;
    });
  }

  removeItem(productId) {
    this.cart.cartItems = this.cart.cartItems.filter(item => item.productId !== productId);
/*    this.networkService.updateCart(this.cart).subscribe((response) => {
      this.cart = response;
    });*/
  }

  selectEvent(item) {
    console.log(item.productId);
    let tempItem: CartItem;
    tempItem.productId = item.productId;
    tempItem.userId = item.userId;
    tempItem.quantity = item.quantity;
    item.productPrices.forEach(prices =>{
      if (prices.productPriceTypeId == 'LIST_PRICE') {
        tempItem.unitPrice = prices.price
      }
    });

    this.cart.cartItems.push(tempItem);
/*    this.networkService.updateCart(this.cart).subscribe((response) => {
      this.cart = response;
    });*/
  }

  onChangeSearch(val: string) {
    this.networkService.productSearch(val).subscribe(e => this.data = e, error => console.log(error));
  }

  onClosed(e) {
  }

  onCleared(e) {
  }

  onFocused(e) {
  }
}
