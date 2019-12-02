import { __decorate, __metadata } from "tslib";
import { Component } from '@angular/core';
import { NetworkService } from '../service/network.service';
import { FormControl, FormGroup } from '@angular/forms';
import { MessageService } from '../service/message.service';
import { saveAs } from 'file-saver';
import { UserService } from "../service/user.service";
var OrderComponent = /** @class */ (function () {
    function OrderComponent(networkService, messageService, userService) {
        this.networkService = networkService;
        this.messageService = messageService;
        this.userService = userService;
        this.orderForm = new FormGroup({
            orderId: new FormControl(''),
        });
        this.showHide = false;
        this.orderDetails = new Array();
        messageService.clear();
    }
    OrderComponent.prototype.ngOnInit = function () {
    };
    OrderComponent.prototype.getOrder = function () {
        var _this = this;
        this.order = this.orderForm.value;
        console.log(this.order.orderId);
        this.networkService.getOrder(this.order.orderId).subscribe(function (res) {
            _this.orderDetail = res;
            _this.showHide = false;
        });
    };
    OrderComponent.prototype.getUserOrder = function () {
        var _this = this;
        console.log(this.userService.id);
        this.networkService.getUserOrder(this.userService.id).subscribe(function (res) {
            console.log(res);
            res.forEach(function (value) {
                console.log("value", value);
                var order;
                order = value;
                _this.orderDetails.push(order);
            });
            _this.showHide = true;
        });
    };
    OrderComponent.prototype.getOrderPDF = function () {
        this.networkService.getOrderPDF(this.orderForm.value).subscribe(function (response) {
            var file = new Blob([response], { type: 'application/pdf' });
            saveAs(file, 'order.pdf');
        });
    };
    OrderComponent = __decorate([
        Component({
            selector: 'app-order',
            templateUrl: './order.component.html'
        }),
        __metadata("design:paramtypes", [NetworkService, MessageService, UserService])
    ], OrderComponent);
    return OrderComponent;
}());
export { OrderComponent };
//# sourceMappingURL=order.component.js.map