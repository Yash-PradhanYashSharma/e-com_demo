import {Component, OnInit} from '@angular/core';
import {NetworkService} from '../network.service';
import {Cart} from './Cart';
import {MessageService} from '../message.service';
import {CartResponse} from './CartResponse';
import {Message} from "../messages/Message";
import {UserService} from "../user.service";
import {formatDate} from '@angular/common';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  private cart: Cart;
  private cartResponse: CartResponse;

  product = 'name';
  data = [];

  selectEvent(item) {
    this.data = [];
  }

  onChangeSearch(val: string) {
    this.data = [];
    this.networkService.productSearch(val).subscribe((response) => {
      this.data = response;
    });
  }

  onFocused(e) {
    this.data = [];
  }

  constructor(private networkService: NetworkService, private messageService: MessageService, private userService: UserService) {
    messageService.clear();
    if (userService.isUserLoggedIn()) {
      messageService.add(new Message('', 'Please Log In', '', 'danger'));
    }
  }

  ngOnInit() {
    this.cart.userId = this.userService.id;
    this.cart.createdBy = this.userService.name;
    this.cart.orderDate = formatDate(new Date(), 'dd/MM/yyyy', 'en');
    this.cart.products = [];
    this.networkService.initializeCart(this.cart).subscribe((response) => {
      this.cart = response;
    });
  }

  getCart() {
    this.networkService.updateCart(this.cart).subscribe((res) => {
      this.cartResponse = res;
    });
  }

}
