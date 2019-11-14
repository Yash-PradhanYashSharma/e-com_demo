import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CartComponent} from './cart/cart.component';
import {InvoiceComponent} from './invoice/invoice.component';
import {OrderComponent} from './order/order.component';
import {OktaCallbackComponent} from "@okta/okta-angular";

const routes: Routes = [
  {path: 'cart', component: CartComponent},
  {path: 'order', component: OrderComponent},
  {path: 'invoice', component: InvoiceComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
