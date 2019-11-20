import {Injectable} from '@angular/core';
import {AuthConfig, JwksValidationHandler, OAuthService} from "angular-oauth2-oidc";

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
    console.log(this.oauthService.getAccessToken());
  }

  logout() {
    this.oauthService.logOut();
  }

}
