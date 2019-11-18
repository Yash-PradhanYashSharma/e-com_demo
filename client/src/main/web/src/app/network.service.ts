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
import {Endpoints} from "./endpoints";
import {CartItem} from "./class/CartItem";
import {CartItemDetails} from "./class/CartItemDetails";

@Injectable({
  providedIn: 'root'
})
export class NetworkService {

  constructor(private http: HttpClient, private messageService: MessageService, public oauthService: OAuthService) {
  }

  initializeCart(cart: Cart): Observable<any> {
    return this.http.post(Endpoints.initializeCart, JSON.stringify(cart), this.getJsonHeader()).pipe(
      catchError(this.handleError<any>('Initialize Cart', [])));
  }

  updateCart(cart: Cart): Observable<any> {
    return this.http.post(Endpoints.updateCart, JSON.stringify(cart), this.getJsonHeader()).pipe(
      catchError(this.handleError<any>('Update Cart', [])));
  }

  productSearch(keyword: String): Observable<CartItemDetails> {
    const finalURL = Endpoints.productSearch + '?keyword=' + keyword;
    return this.http.get(finalURL).pipe(
      catchError(this.handleError<any>('productSearch', []))
    );
  }

  getParty(): Observable<any> {
    const finalURL = Endpoints.partyDetails;
    return this.http.get(finalURL).pipe(
      catchError(this.handleError<any>('getParty', [])));
  }

  getInvoice(invoice: InvoiceRequest): Observable<any> {
    const finalURL = Endpoints.invoiceUrl + '/' + invoice.invoiceId;
    return this.http.get(finalURL, this.getOktaHeaderWithToken()).pipe(
      tap((invoiceResp: Invoice) => {
        this.log(invoiceResp.id, 'Get Invoice', '');
      }), catchError(this.handleError<any>('getInvoice', [])));
  }

  getInvoicePDF(invoice: InvoiceRequest): Observable<any> {
    const finalURL = Endpoints.invoiceUrl + '/' + invoice.invoiceId + '?type=pdf';
    return this.http.get(finalURL, {
      headers: this.httpOptions.headers,
      responseType: 'blob' as 'json'
    }).pipe(catchError(this.handleError<any>('getInvoicePDF', [])));
  }

  getOrderPDF(order: Order): Observable<any> {
    const finalURL = Endpoints.invoiceUrl + '/' + order.orderId + '?type=pdf';
    return this.http.get(finalURL, {
      headers: this.httpOptions.headers,
      responseType: 'blob' as 'json'
    }).pipe(catchError(this.handleError<any>('getOrderPDF', [])));
  }

  getOrder(order: Order): Observable<OrderResponse> {
    return this.http.post(Endpoints.orderUrl, order, this.httpOptions).pipe(
      tap((orderResp: OrderResponse) => {
        this.log(orderResp.orderId, 'Order #', '');
      }), catchError(this.handleError<any>('Order Response', [])));
  }

  createOrder(cart: Cart): Observable<CartResponse> {
    console.log(JSON.stringify(cart))
    return this.http.post<CartResponse>(Endpoints.createOrder, JSON.stringify(cart), this.httpOptions).pipe(
      tap((cartResp: CartResponse) => {
        this.log(cartResp.userId, cartResp.status, '');
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
    console.log('accessToken: ', accessToken);
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

