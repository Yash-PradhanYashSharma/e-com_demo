import {Injectable} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  id: string;
  name: string;

  constructor(public oauthService: OAuthService) {
  }

  isUserLoggedIn() {
    return this.userDetails() != null;
  }

  userDetails() {
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
