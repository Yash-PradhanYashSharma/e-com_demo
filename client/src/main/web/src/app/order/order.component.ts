import {Component, OnInit} from '@angular/core';
import {NetworkService} from '../service/network.service';
import {FormControl, FormGroup} from '@angular/forms';
import {MessageService} from '../service/message.service';
import {OrderDetails} from "../class/Order";
import {UserService} from "../service/user.service";
import {Router} from "@angular/router";
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
              private userService: UserService, private router: Router) {
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
      console.log(res);
      res.forEach(value => {
        this.orderDetails.push(value);
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
