import {Injectable} from '@angular/core';
import {Message} from '../class/Message';

@Injectable({
  providedIn: 'root',
})

export class MessageService {
  message: Message;

  add(message) {
    this.message = message;
  }

  clear() {
    this.message = null;
  }
}
