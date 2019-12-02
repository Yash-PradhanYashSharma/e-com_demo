import {Injectable} from '@angular/core';
import {MessageService} from './message.service';
import {Cart, CartResponse} from '../class/Cart';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Order, OrderDetails} from '../class/Order';
import {Message} from '../class/Message';
import {Invoice} from '../class/Invoice';
import {environment} from "../../environments/environment";
import {LoginService} from "./login.service";
import {Shipment} from "../class/Shipment";

@Injectable({
  providedIn: 'root'
})
export class NetworkService {

  constructor(private http: HttpClient, private messageService: MessageService, public loginService: LoginService) {
  }

  initializeCart(cart: Cart): Observable<any> {
    return this.http.post(environment.initializeCart, JSON.stringify(cart), this.loginService.muleHttpOptions()).pipe(
      catchError(this.handleError<any>('Initialize Cart', [])));
  }

  updateCart(cart: Cart): Observable<any> {
    return this.http.post(environment.updateCart, JSON.stringify(cart), this.loginService.muleHttpOptions()).pipe(
      catchError(this.handleError<any>('Update Cart', [])));
  }

  getFreight(freight: Shipment): Observable<any> {
    return this.http.post(environment.getShipmentDetails, JSON.stringify(freight), this.loginService.muleHttpOptions()).pipe(
      catchError(this.handleError<any>('Get Freight', [])));
  }

  productSearch(keyword: String): Observable<any> {
    const finalURL = environment.productSearch + '?keyword=' + keyword;
    return this.http.get(finalURL, this.loginService.muleHttpOptions()).pipe(
      catchError(this.handleError<any>('productSearch', []))
    );
  }

  createOrder(cart: Cart): Observable<CartResponse> {
    return this.http.post<CartResponse>(environment.order, cart, this.loginService.muleHttpOptions()).pipe(
      tap((cartResp: any) => {
        console.log(cartResp);
        this.log(cartResp.orderId, cartResp.message, '');
      }), catchError(this.handleError<any>('createOrder', [])));
  }

  getOrder(orderId: string): Observable<OrderDetails> {
    return this.http.post(environment.getOrder, {
      'orderId': orderId
    }, this.loginService.muleHttpOptions()).pipe(
      catchError(this.handleError<any>('Order Response', [])));
  }

  getOrderPDF(order: Order): Observable<any> {
    const finalURL = environment.getOrder + '?type=pdf';
    return this.http.post(finalURL, {
        'orderId': order.orderId,
        'orderTotal': order.total,
        'userName': order.user_id
      }, this.loginService.mulePdfHttpOptions()
    ).pipe(catchError(this.handleError<any>('getOrderPDF', [])));
  }

  getUserOrder(userId: string): Observable<OrderDetails[]> {
    const finalURL = environment.getUserOrder + '?userId=' + userId;
    return this.http.get(finalURL, this.loginService.muleHttpOptions()).pipe(
      catchError(this.handleError<any>('User Order Response', [])));
  }

  getParty(): Observable<any> {
    const finalURL = environment.partyDetails;
    return this.http.get(finalURL).pipe(
      catchError(this.handleError<any>('getParty', [])));
  }

  getInvoice(invoice: string): Observable<Invoice> {
    const finalURL = environment.invoiceUrl + '/' + invoice;
    return this.http.get(finalURL, this.loginService.stripeHttpOptions()).pipe(
      tap((invoiceResp: Invoice) => {
        this.log(invoiceResp.id, 'Get Invoice', '');
      }), catchError(this.handleError<any>('getInvoice', [])));
  }

  getInvoicePDF(invoiceId): Observable<any> {
    const finalURL = environment.invoiceUrl + '/' + invoiceId + '?type=pdf';
    return this.http.get(finalURL, this.loginService.stripeHttpOptions()).pipe(catchError(this.handleError<any>('getInvoicePDF', [])));
  }


  showInvoicePDF(url: any): any {
    return this.http.get(url, {responseType: 'blob'}).pipe(catchError(this.handleError<any>('showInvoicePDF', [])));
  }

  serverLogin() {
    const credentials = {username: 'admin', password: 'admin'};
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get(environment.serverLogin, {headers: headers}).subscribe((response) => {
      console.log('Server Login Successful ', response);
    });
  };

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
}

