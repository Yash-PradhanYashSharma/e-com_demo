import {Component, OnInit} from '@angular/core';
import {NetworkService} from '../service/network.service';
import {FormControl, FormGroup} from '@angular/forms';
import {MessageService} from '../service/message.service';
import {saveAs} from 'file-saver';
import {OrderDetails} from "../class/Order";
import {UserService} from "../service/user.service";

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

  constructor(private networkService: NetworkService, private messageService: MessageService, private userService: UserService) {
    messageService.clear();
  }

  ngOnInit() {
  }

  getOrder(): void {
    this.order = this.orderForm.value;
    console.log(this.order.orderId);
    this.networkService.getOrder(this.order.orderId).subscribe((res) => {
      this.orderDetail = res;
      this.showHide = false;
    });
  }

  getUserOrder(): void {
    console.log(this.userService.id);
    this.networkService.getUserOrder(this.userService.id).subscribe((res) => {
      console.log(res);
      res.forEach(value => {
        console.log("value", value);
        let order: OrderDetails;
        order = value;
        this.orderDetails.push(order);
      });
      this.showHide = true;
    });
  }

  getOrderPDF() {
    this.networkService.getOrderPDF(this.orderForm.value).subscribe((response) => {
      let file = new Blob([response], {type: 'application/pdf'});
      saveAs(file, 'order.pdf');
    });
  }
}
