import {Component} from '@angular/core';
import {LoginService} from "./service/login.service";
import {UserService} from "./service/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'MuleSoft Integration Middleware Accelerator';
  isAuthenticated: boolean = false;
  userName: string;
  userEmail: string;
  date: Date = new Date();

  constructor(private loginService: LoginService, private userService: UserService) {
  }

  ngDoCheck() {
    this.isAuthenticated = this.userService.isUserLoggedIn();
    this.userName = this.userService.name;
    this.userEmail = this.userService.id;
  }

  login() {
    this.loginService.login();
  }

  logout() {
    this.loginService.logout();
  }

}
