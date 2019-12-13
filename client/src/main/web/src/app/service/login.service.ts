import {Injectable} from '@angular/core';
import {AuthConfig, JwksValidationHandler, OAuthService} from "angular-oauth2-oidc";
import {HttpHeaders} from "@angular/common/http";

export const authConfig: AuthConfig = {
  issuer: 'https://dev-589498.okta.com/oauth2/default',
  redirectUri: 'http://localhost:4200/cart',
  clientId: '0oa1scmigkSfQlWpB357'
};

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  accessToken: string;
  constructor(private oauthService: OAuthService) {
    this.configure();
  }

  public configure() {
    this.oauthService.configure(authConfig);
    this.oauthService.tokenValidationHandler = new JwksValidationHandler();
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
    this.oauthService.setupAutomaticSilentRefresh();
    this.accessToken = this.oauthService.getAccessToken();
  }

  login() {
    this.oauthService.initImplicitFlow();
  }

  getAccessToken() {
    this.accessToken = this.oauthService.getAccessToken();
    return this.accessToken;
  }
  logout() {
    this.oauthService.logOut();
  }

  muleHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.accessToken,
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      })
    };
  }

  serviceNowHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Authorization': 'Basic QmV0aC5BbmdsaW46c25vdyEwOTg=',
        'Content-Type': 'application/json'
      })
    };
  }

  mulePdfHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.accessToken,
        'Content-Type': 'application/json'
      }), responseType: 'blob' as 'json'
    };
  }

  stripeHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.accessToken,
        'Content-Type': 'application/json'
      })
    };
  }

}
