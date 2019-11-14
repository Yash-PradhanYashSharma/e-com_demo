import {Component} from '@angular/core';
import {LoginService} from "./login.service";
import {UserService} from "./user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  isAuthenticated: boolean = false;

  constructor(private loginService: LoginService, private userService: UserService) {
  }

  ngDoCheck() {
    this.isAuthenticated = this.userService.isUserLoggedIn();
  }

  login() {
    this.loginService.login();
  }

  logout() {
    this.loginService.logout();
  }

  private static host = 'localhost';
  private static host2 = '10.26.9.41';
  private static okta = 'dev-589498.okta.com';
  public static userDetails = `http://${AppComponent.okta}/api/v1/users/me`;
  public static partyDetails = `http://${AppComponent.host}:8080/api/party/all`;
  public static initializeCart = `http://${AppComponent.host}:8080/api/cart/initialize`;
  public static productSearch = `http://${AppComponent.host}:8080/product/search`;
  public static serverLogin = `http://${AppComponent.host}:8080/login`;
  public static cartUrl = `http://${AppComponent.host}:8081/v1/api/shipments/cart`;
  public static orderUrl = `http://${AppComponent.host}:8081/v1/api/shipments/order`;
  public static invoiceUrl = `http://${AppComponent.host2}:8081/api/invoice`;
  title = 'MuleSoft Integration Middleware Accelerator';

}
