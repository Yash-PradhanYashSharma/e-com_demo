import {Component} from '@angular/core';
import {LoginService} from "./login.service";
import {UserService} from "./user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'MuleSoft Integration Middleware Accelerator';
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

}
