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

  constructor(private oauthService: OAuthService) {
    this.configure();
  }

  private configure() {
    this.oauthService.configure(authConfig);
    this.oauthService.tokenValidationHandler = new JwksValidationHandler();
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
    this.oauthService.setupAutomaticSilentRefresh();
  }

  login() {
    this.oauthService.initImplicitFlow();
  }

  logout() {
    this.oauthService.logOut();
  }

  muleHttpOptions() {
    const accessToken = this.oauthService.getAccessToken();
    return {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + accessToken,
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      })
    };
  }

  mulePdfHttpOptions() {
    const accessToken = this.oauthService.getAccessToken();
    return {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + accessToken,
        'Content-Type': 'application/json'
      }), responseType: 'blob' as 'json'
    };
  }

  stripeHttpOptions() {
    const accessToken = this.oauthService.getAccessToken();
    return {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + accessToken,
        'Content-Type': 'application/json'
      })
    };
  }

}
