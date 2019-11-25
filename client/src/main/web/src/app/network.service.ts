import {Injectable} from '@angular/core';
import {MessageService} from './message.service';
import {Cart} from './class/Cart';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Order} from './order/Order';
import {InvoiceRequest} from './invoice/InvoiceRequest';
import {Message} from './messages/Message';
import {Invoice} from './invoice/Invoice';
import {OrderResponse} from "./order/OrderResponse";
import {CartResponse} from "./class/CartResponse";
import {OAuthService} from 'angular-oauth2-oidc';
import {environment} from "../environments/environment";
import {CartItemDetail} from "./class/CartItemDetail";

@Injectable({
  providedIn: 'root'
})
export class NetworkService {

  constructor(private http: HttpClient, private messageService: MessageService, public oauthService: OAuthService) {
  }

  initializeCart(cart: Cart): Observable<any> {
    console.log(JSON.stringify(cart));
    return this.http.post(environment.initializeCart, JSON.stringify(cart), this.getOktaHeaderWithToken()).pipe(
      catchError(this.handleError<any>('Initialize Cart', [])));
  }

  updateCart(cart: Cart): Observable<any> {
    return this.http.post(environment.updateCart, JSON.stringify(cart), this.getOktaHeaderWithToken()).pipe(
      catchError(this.handleError<any>('Update Cart', [])));
  }

  getFreight(freight: any): Observable<any> {
    return this.http.post(environment.getShipmentDetails, freight, this.getOktaHeaderWithToken()).pipe(
      catchError(this.handleError<any>('Get Freight', [])));
  }

  productSearch(keyword: String): Observable<any> {
    const finalURL = environment.productSearch + '?keyword=' + keyword;
    return this.http.get(finalURL, this.getOktaHeaderWithToken()).pipe(
      catchError(this.handleError<any>('productSearch', []))
    );
  }

  getParty(): Observable<any> {
    const finalURL = environment.partyDetails;
    return this.http.get(finalURL).pipe(
      catchError(this.handleError<any>('getParty', [])));
  }

  getInvoice(invoice: InvoiceRequest): Observable<any> {
    const finalURL = environment.invoiceUrl + '/' + invoice.invoiceId;
    return this.http.get(finalURL, this.getOktaHeaderWithToken()).pipe(
      tap((invoiceResp: Invoice) => {
        this.log(invoiceResp.id, 'Get Invoice', '');
      }), catchError(this.handleError<any>('getInvoice', [])));
  }

  getInvoicePDF(invoice: InvoiceRequest): Observable<any> {
    const finalURL = environment.invoiceUrl + '/' + invoice.invoiceId + '?type=pdf';
    return this.http.get(finalURL, {
      headers: this.httpOptions.headers,
      responseType: 'blob' as 'json'
    }).pipe(catchError(this.handleError<any>('getInvoicePDF', [])));
  }

  getOrderPDF(order: Order): Observable<any> {
    const finalURL = environment.invoiceUrl + '/' + order.orderId + '?type=pdf';
    return this.http.get(finalURL, {
      headers: this.httpOptions.headers,
      responseType: 'blob' as 'json'
    }).pipe(catchError(this.handleError<any>('getOrderPDF', [])));
  }

  getOrder(order: Order): Observable<OrderResponse> {
    return this.http.post(environment.orderUrl, order, this.httpOptions).pipe(
      tap((orderResp: OrderResponse) => {
        this.log(orderResp.orderId, 'Order #', '');
      }), catchError(this.handleError<any>('Order Response', [])));
  }

  createOrder(cart: Cart): Observable<CartResponse> {
    console.log('------------',cart);
    return this.http.post<CartResponse>(environment.order, cart, this.httpOptions).pipe(
      tap((cartResp: any) => {
        console.log(cartResp);
      }), catchError(this.handleError<any>('createOrder', [])));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error): Observable<T> => {
      this.logError('', 'Oops! Something went wrong. We\'ll fix it Soon. report error', '');
      console.error(operation);
      console.error(error);
      return of(result as T);
    };
  }

  private log(code, message, link) {
    this.messageService.add(new Message(code, message, link, 'success'));
  }

  private logError(code, message, link) {
    this.messageService.add(new Message(code, message, link, 'danger'));
  }

  private getOktaHeaderWithToken() {
    const accessToken = this.oauthService.getAccessToken();
    //console.log('accessToken: ', accessToken);
    return {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + accessToken,
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      })
    };
  }

  private getJsonHeader() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      })
    };
  }

  httpOptions = {
    headers: new HttpHeaders({
      'userName': 'admin',
      'password': 'password'
    })
  };
}

