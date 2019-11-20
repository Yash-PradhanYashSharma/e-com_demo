import {Injectable} from '@angular/core';
import {AppComponent} from "./app.component";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {OAuthService} from "angular-oauth2-oidc";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  id: string;
  name: string;

  constructor(public oauthService: OAuthService, public http: HttpClient) {
  }

  getUserDetails() {
    const accessToken = this.oauthService.getAccessToken();
    //console.log('accessToken: ', accessToken);
    const httpMuleServerOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + accessToken,
        'Accept': 'application/json'
      })
    };
    this.http.get(environment.userDetails, httpMuleServerOptions).subscribe((response) => {
      console.log(response);
    });
  }

  isUserLoggedIn() {
    return this.userDetails() != null;
  }

  private userDetails() {
    const claims = this.oauthService.getIdentityClaims();
    if (!claims) {
      return null;
    }
    //console.log('claims', claims);
    this.name = claims['name'];
    this.id = claims['preferred_username'];
    return claims['name'];
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
}
