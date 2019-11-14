import {Component, OnInit} from '@angular/core';
import {NetworkService} from '../network.service';
import {Invoice} from './Invoice';
import {Lines} from './Lines';
import {FormControl, FormGroup} from '@angular/forms';
import {saveAs} from 'file-saver';
import {MessageService} from '../message.service';

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
    this.networkService.getInvoice(this.invoiceForm.value).subscribe(res => {
      this.invoiceValue = res;
      const lines = res['lines'];
      this.lines = lines['data'];
    });
  }

  getInvoicePDF() {
    this.networkService.getInvoicePDF(this.invoiceForm.value).subscribe((response) => {
      let file = new Blob([response], { type: 'application/pdf' });
      saveAs(file, 'invoice.pdf');
    });
  }
}
