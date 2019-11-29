import {Component, OnInit} from '@angular/core';
import {NetworkService} from '../service/network.service';
import {Invoice} from '../class/Invoice';
import {Lines} from '../class/Lines';
import {FormControl, FormGroup} from '@angular/forms';
import {MessageService} from '../service/message.service';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {

  invoiceValue: Invoice;
  lines: Lines[];
  src2: string;

  invoiceForm = new FormGroup({
    invoiceId: new FormControl(''),
  });

  constructor(private networkService: NetworkService, private messageService: MessageService) {
    messageService.clear();
  }

  ngOnInit() {
  }

  getInvoice(): void {
    /*
  in_1Fjjd4LtNhj8WVidJeDHkKOP
     */
    this.networkService.getInvoice(this.invoiceForm.value).subscribe(res => {
      this.invoiceValue = res;
      //console.log(res)
    });
  }

  getInvoicePDF() {
    this.networkService.getInvoicePDF(this.invoiceForm.value).subscribe((response) => {
      console.log('-----', response.url);
      this.networkService.showInvoicePDF(response.url).subscribe(data => {
        window.location.href = URL.createObjectURL(data);
      });
    });
  }
}
