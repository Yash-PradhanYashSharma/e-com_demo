import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {PdfViewerModule} from 'ng2-pdf-viewer';
import {AppComponent} from './app.component';
import {CartComponent} from './cart/cart.component';
import {OrderComponent} from './order/order.component';
import {InvoiceComponent} from './invoice/invoice.component';
import {MessagesComponent} from './messages/messages.component';
import {AppRoutingModule} from './app-routing.module';
import {AutocompleteLibModule} from "angular-ng-autocomplete";
import {OAuthModule} from 'angular-oauth2-oidc';
import { AutocompleteModule } from 'ng2-input-autocomplete';


@NgModule({
  declarations: [
    AppComponent,
    CartComponent,
    OrderComponent,
    InvoiceComponent,
    MessagesComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AutocompleteLibModule,
    AppRoutingModule,
    ReactiveFormsModule,
    PdfViewerModule,
    HttpClientModule,
    OAuthModule.forRoot(),
    AutocompleteModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
