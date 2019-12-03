import {Component, OnInit} from '@angular/core';
import {NetworkService} from '../service/network.service';
import {FormControl, FormGroup} from '@angular/forms';
import {MessageService} from '../service/message.service';
import {OrderDetails} from "../class/Order";
import {UserService} from "../service/user.service";
import {saveAs} from 'file-saver';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html'
})
export class OrderComponent implements OnInit {

  orderForm = new FormGroup({
    orderId: new FormControl(''),
  });

  showHide: boolean = false;
  orderDetail: OrderDetails;
  orderDetails: OrderDetails[] = new Array<OrderDetails>();
  private order: any;

  constructor(private networkService: NetworkService, private messageService: MessageService,
              private userService: UserService) {
    messageService.clear();
  }

  ngOnInit() {
  }

  getOrder(): void {
    this.order = this.orderForm.value;
    this.networkService.getOrder(this.order.orderId).subscribe((res) => {
      this.orderDetail = res;
      this.showHide = false;
    });
  }

  getUserOrder(): void {
    console.log(this.userService.id);
    this.networkService.getUserOrder(this.userService.id).subscribe((res) => {
      res.forEach(value => {
        this.orderDetails.push(value);
      });
      this.showHide = true;
    });
  }

  getOrderPDF() {
    this.order = this.orderForm.value;
    this.networkService.getOrder(this.order.orderId).subscribe((res) => {
      this.orderDetail = res;
      this.showHide = false;
      this.networkService.getOrderPDF(this.orderDetail.orders.orderId, this.orderDetail.orders.total, this.orderDetail.orders.user_id).subscribe((response) => {
        saveAs(new Blob([response], {type: 'application/pdf'}), this.orderDetail.orders.orderId + '.pdf');
      });
    });
  }
}
