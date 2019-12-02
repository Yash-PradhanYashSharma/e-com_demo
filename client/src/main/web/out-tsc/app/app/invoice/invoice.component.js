import { __decorate, __metadata } from "tslib";
import { Component } from '@angular/core';
import { NetworkService } from '../service/network.service';
import { FormControl, FormGroup } from '@angular/forms';
import { MessageService } from '../service/message.service';
var InvoiceComponent = /** @class */ (function () {
    function InvoiceComponent(networkService, messageService) {
        this.networkService = networkService;
        this.messageService = messageService;
        this.invoiceForm = new FormGroup({
            invoiceId: new FormControl(''),
        });
        messageService.clear();
    }
    InvoiceComponent.prototype.ngOnInit = function () {
    };
    InvoiceComponent.prototype.getInvoice = function () {
        var _this = this;
        /*
      in_1Fjjd4LtNhj8WVidJeDHkKOP
         */
        this.networkService.getInvoice(this.invoiceForm.value).subscribe(function (res) {
            _this.invoiceValue = res;
            //console.log(res)
        });
    };
    InvoiceComponent.prototype.getInvoicePDF = function () {
        var _this = this;
        this.networkService.getInvoicePDF(this.invoiceForm.value).subscribe(function (response) {
            console.log('-----', response.url);
            _this.networkService.showInvoicePDF(response.url).subscribe(function (data) {
                window.location.href = URL.createObjectURL(data);
            });
        });
    };
    InvoiceComponent = __decorate([
        Component({
            selector: 'app-invoice',
            templateUrl: './invoice.component.html',
            styleUrls: ['./invoice.component.css']
        }),
        __metadata("design:paramtypes", [NetworkService, MessageService])
    ], InvoiceComponent);
    return InvoiceComponent;
}());
export { InvoiceComponent };
//# sourceMappingURL=invoice.component.js.map