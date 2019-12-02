import {Component, OnInit} from '@angular/core';
import {Invoice, Lines} from '../class/Invoice';
import {MessageService} from '../service/message.service';
import {ActivatedRoute, ParamMap} from "@angular/router";
import {switchMap} from "rxjs/operators";
import {NetworkService} from "../service/network.service";

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html'
})
export class InvoiceComponent implements OnInit {

  invoiceValue: Invoice;
  lines: Lines;
  invoiceId: string;

  constructor(private messageService: MessageService, private route: ActivatedRoute, private networkService: NetworkService) {
    messageService.clear();
  }

  ngOnInit() {
    this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        this.invoiceId = params.get('id'))).subscribe(resp => {
      this.getInvoice(this.invoiceId);
    });
  }

  getInvoice(invoiceId): void {
    this.networkService.getInvoice(invoiceId).subscribe(res => {
      this.invoiceValue = res;
      this.lines = res['lines']['data'];
      console.log(res)
    });
  }

  getInvoicePDF() {
    this.networkService.getInvoicePDF(this.invoiceId).subscribe((response) => {
      this.networkService.showInvoicePDF(response.url).subscribe(data => {
        window.location.href = URL.createObjectURL(data);
      });
    });
  }
}
