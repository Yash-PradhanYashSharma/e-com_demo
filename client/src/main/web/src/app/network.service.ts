import {Injectable} from '@angular/core';
import {MessageService} from './message.service';
import {Cart} from './cart/Cart';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Order} from './order/Order';
import {InvoiceRequest} from './invoice/InvoiceRequest';
import {Message} from './messages/Message';
import {AppComponent} from './app.component';
import {Invoice} from './invoice/Invoice';
import {OrderResponse} from "./order/OrderResponse";
import {CartResponse} from "./cart/CartResponse";
import {OAuthService} from 'angular-oauth2-oidc';

@Injectable({
  providedIn: 'root'
})
export class NetworkService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'client_id': 'cd8b515f39b4459ba064ff571c515334',
      'client_secret': '615175E0C7C2486581026e273633C899'
    })
  };

  constructor(private http: HttpClient, private messageService: MessageService, public oauthService: OAuthService) {
  }

  initializeCart(cart: Cart): Observable<any> {
    return this.http.post(AppComponent.initializeCart, cart).pipe(
      catchError(this.handleError<any>('Initialize Cart', [])));
  }

  productSearch(keyword: String): Observable<any> {
    const finalURL = AppComponent.productSearch + '?keyword=' + keyword;
    return this.http.get(finalURL).pipe(
      catchError(this.handleError<any>('productSearch', []))
    );
  }

  getParty(): Observable<any> {
    const finalURL = AppComponent.partyDetails;
    return this.http.get(finalURL).pipe(
      catchError(this.handleError<any>('getParty', [])));
  }

  getInvoice(invoice: InvoiceRequest): Observable<any> {
    const finalURL = AppComponent.invoiceUrl + '/' + invoice.invoiceId;
    return this.http.get(finalURL, this.getMuleHeaderWithToken()).pipe(
      tap((invoiceResp: Invoice) => {
        this.log(invoiceResp.id, 'Get Invoice', '');
      }), catchError(this.handleError<any>('getInvoice', [])));
  }

  getInvoicePDF(invoice: InvoiceRequest): Observable<any> {
    const finalURL = AppComponent.invoiceUrl + '/' + invoice.invoiceId + '?type=pdf';
    return this.http.get(finalURL, {
      headers: this.httpOptions.headers,
      responseType: 'blob' as 'json'
    }).pipe(catchError(this.handleError<any>('getInvoicePDF', [])));
  }

  getOrderPDF(order: Order): Observable<any> {
    const finalURL = AppComponent.invoiceUrl + '/' + order.orderId + '?type=pdf';
    return this.http.get(finalURL, {
      headers: this.httpOptions.headers,
      responseType: 'blob' as 'json'
    }).pipe(catchError(this.handleError<any>('getOrderPDF', [])));
  }

  getOrder(order: Order): Observable<OrderResponse> {
    return this.http.post(AppComponent.orderUrl, order, this.httpOptions).pipe(
      tap((orderResp: OrderResponse) => {
        this.log(orderResp.orderId, 'Order #', '');
      }), catchError(this.handleError<any>('Order Response', [])));
  }

  updateCart(cart: Cart): Observable<CartResponse> {
    return this.http.post<CartResponse>(AppComponent.cartUrl, JSON.stringify(cart), this.httpOptions).pipe(
      tap((cartResp: CartResponse) => {
        this.log(cartResp.orderId, cartResp.status, '');
      }), catchError(this.handleError<any>('updateCart', [])));
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

  private getMuleHeaderWithToken() {
    const accessToken = this.oauthService.getAccessToken();
    console.log('accessToken: ', accessToken);
    return {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + accessToken,
      })
    };
  }
}

