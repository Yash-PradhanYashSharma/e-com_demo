import {Component, OnInit} from '@angular/core';
import {NetworkService} from '../network.service';
import {FormControl, FormGroup} from '@angular/forms';
import {MessageService} from '../message.service';
import {saveAs} from 'file-saver';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  orderForm = new FormGroup({
    orderId: new FormControl(''),
  });

  cart;

  constructor(private networkService: NetworkService, private messageService: MessageService) {
    messageService.clear();
  }

  ngOnInit() {
  }

  getOrder(): void {
    this.networkService.getOrder(this.orderForm.value).subscribe((res) => {
      this.cart = res;
    });
  }

  getOrderPDF() {
    this.networkService.getOrderPDF(this.orderForm.value).subscribe((response) => {
      let file = new Blob([response], { type: 'application/pdf' });
      saveAs(file, 'order.pdf');
    });
  }
}
