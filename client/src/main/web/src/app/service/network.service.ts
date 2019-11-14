import {Injectable} from '@angular/core';
import {Observable, of} from "rxjs";
import {Endpoints} from "../endpoints";
import {HttpClient} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {MessageService} from "./message.service";
import {Message} from "../class/Message";

@Injectable({
  providedIn: 'root'
})
export class NetworkService {

  constructor(private http: HttpClient, private messageService: MessageService) {
  }

  getParty(): Observable<any> {
    const finalURL = Endpoints.party;
    console.log('-----------',finalURL);
    return this.http.get(finalURL).pipe(catchError(this.handleError<any>('getParty', [])));
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
}
