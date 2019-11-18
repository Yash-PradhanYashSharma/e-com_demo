import {Component, OnInit} from '@angular/core';
import {NetworkService} from '../network.service';
import {Cart} from '../class/Cart';
import {MessageService} from '../message.service';
import {CartResponse} from '../class/CartResponse';
import {UserService} from "../user.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  private cart: Cart = new Cart();
  private cartResponse: CartResponse;
  data = [];
  keyword = 'productName';

  constructor(private networkService: NetworkService, private messageService: MessageService, private userService: UserService, private http: HttpClient) {
    messageService.clear();
  }

  ngOnInit() {
    this.cart.userId = this.userService.id;
    this.cart.cartDate = new Date();
    this.cart.cartItems = [];
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
    this.networkService.updateCart(this.cart).subscribe((response) => {
      this.cart = response;
    });

  }

  selectEvent(item) {
    this.cart.cartItems.push(item);
    this.networkService.updateCart(this.cart).subscribe((response) => {
      this.cart = response;
    });
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
