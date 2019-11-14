import {Injectable} from '@angular/core';
import {AppComponent} from "./app.component";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {OAuthService} from "angular-oauth2-oidc";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  name: string;
  id: string;

  constructor(public oauthService: OAuthService, public http: HttpClient) {
  }

  getUserDetails() {
    const finalURL = AppComponent.userDetails;
    const accessToken = this.oauthService.getAccessToken();
    console.log('accessToken: ', accessToken);
    const httpMuleServerOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + accessToken,
      })
    };
    this.http.get(finalURL, httpMuleServerOptions).subscribe((response) => {
      console.log(response);
    });
  }

  isUserLoggedIn() {
    const userName = this.userDetails();
    return userName != null;
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
}
