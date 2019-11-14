export class Message {
  code: string;
  messages: string;
  link: string;
  clazz: string;

  constructor(link: string, messages: string, code: string, clazz: string) {
    this.code = code;
    this.messages = messages;
    this.link = link;
    this.clazz = clazz;
  }
}
