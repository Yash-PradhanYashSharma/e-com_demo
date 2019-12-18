import {Injectable} from '@angular/core';
import {MessageService} from './message.service';
import {Cart, CartResponse} from '../class/Cart';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {OrderDetails} from '../class/Order';
import {Message} from '../class/Message';
import {Invoice} from '../class/Invoice';
import {environment} from "../../environments/environment";
import {LoginService} from "./login.service";
import {Shipment} from "../class/Shipment";
import {Incident} from "../class/Incident";

@Injectable({
  providedIn: 'root'
})
export class NetworkService {

  constructor(private http: HttpClient, private messageService: MessageService, private loginService: LoginService) {
  }

  initializeCart(cart: Cart): Observable<any> {
    return this.http.post(environment.initializeCart, JSON.stringify(cart), this.loginService.muleHttpOptions()).pipe(
      catchError(this.handleError<any>('Oops!! Some thing went wrong.', []))
    );
  }

  updateCart(cart: Cart): Observable<any> {
    return this.http.post(environment.updateCart, JSON.stringify(cart), this.loginService.muleHttpOptions()).pipe(
      catchError(this.handleError<any>('Oops!! We are unable to update Cart at this moment.', [])));
  }

  getFreight(freight: Shipment): Observable<any> {
    return this.http.post(environment.getShipmentDetails, JSON.stringify(freight), this.loginService.muleHttpOptions()).pipe(
      catchError(this.handleError<any>('Oops!! We are unable to get Freight charges at this moment.', [])));
  }

  productSearch(keyword: String): Observable<any> {
    const finalURL = environment.productSearch + '?keyword=' + keyword;
    return this.http.get(finalURL, this.loginService.muleHttpOptions()).pipe(
      catchError(this.handleError<any>('Oops!! We are unable to search products at this moment.', []))
    );
  }

  createOrder(cart: Cart): Observable<CartResponse> {
    return this.http.post<CartResponse>(environment.order, cart, this.loginService.muleHttpOptions()).pipe(
      tap((cartResp: any) => {
        this.log(cartResp.orderId, cartResp.message, '');
      }), catchError(this.handleError<any>('Oops!! We are unable to create Order at this moment.', [])));
  }

  getOrder(orderId: string): Observable<OrderDetails> {
    return this.http.post(environment.getOrder, {
      'orderId': orderId
    }, this.loginService.muleHttpOptions()).pipe(
      catchError(this.handleError<any>('Oops!! We are unable to find Order at this moment.', [])));
  }

  getOrderPDF(orderId, total, userName): Observable<any> {
    const finalURL = environment.getOrder + '?type=pdf';
    const body = {
      'orderTotal': total,
      'orderId': orderId,
      'userName': userName
    };
    return this.http.post(finalURL, body, this.loginService.mulePdfHttpOptions()
    ).pipe(catchError(this.handleError<any>('Oops!! We are unable to find Order PFD at this moment.', [])));
  }

  getOrderIncidents(orderId): Observable<Incident[]> {
    const finalURL = environment.incidents + '?type=search';
    const body = {
      "userId": "Beth.Anglin",
      "orderId": orderId
    };
    return this.http.post(finalURL, body, this.loginService.muleHttpOptions()).pipe(
      catchError(this.handleError<any>('Oops!! We are unable to search incidents at this moment.', []))
    );
  }

  createOrderIncidents(orderId, description): Observable<Incident[]> {
    const finalURL = environment.incidents + '?type=create';
    const body = {
      "userId": "Beth.Anglin",
      "orderId": orderId,
      "incidentDescription": description
    };
    return this.http.post(finalURL, body, this.loginService.muleHttpOptions()).pipe(
      catchError(this.handleError<any>('Oops!! We are unable to create incidents at this moment.', []))
    );
  }

  getUserOrder(userId: string): Observable<OrderDetails[]> {
    const finalURL = environment.getUserOrder + '?userId=' + userId;
    return this.http.get(finalURL, this.loginService.muleHttpOptions()).pipe(
      catchError(this.handleError<any>('Oops!! We are unable to find orders at this moment.', [])));
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
      }), catchError(this.handleError<any>('Oops!! There is an error connecting to the SAP Systems.', [])));
  }

  getInvoicePDF(invoiceId): Observable<any> {
    const finalURL = environment.invoiceUrl + '/' + invoiceId + '?type=pdf';
    return this.http.get(finalURL, this.loginService.stripeHttpOptions()).pipe(
      catchError(this.handleError<any>('Oops!! There is an error in finding Invoice PDF from SAP Systems.', [])));
  }

  showInvoicePDF(url: any): any {
    return this.http.get(url, {responseType: 'blob'}).pipe(
      catchError(this.handleError<any>('Oops!! There is an error in finding Invoice from SAP Systems.', [])));
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
      this.logError('', operation, '');
      this.loginService.configure();
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

