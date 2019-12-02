import { __decorate, __metadata } from "tslib";
import { Injectable } from '@angular/core';
import { JwksValidationHandler, OAuthService } from "angular-oauth2-oidc";
import { HttpHeaders } from "@angular/common/http";
export var authConfig = {
    issuer: 'https://dev-589498.okta.com/oauth2/default',
    redirectUri: 'http://localhost:4200/cart',
    clientId: '0oa1scmigkSfQlWpB357'
};
var LoginService = /** @class */ (function () {
    function LoginService(oauthService) {
        this.oauthService = oauthService;
        this.configure();
    }
    LoginService.prototype.configure = function () {
        this.oauthService.configure(authConfig);
        this.oauthService.tokenValidationHandler = new JwksValidationHandler();
        this.oauthService.loadDiscoveryDocumentAndTryLogin();
        this.oauthService.setupAutomaticSilentRefresh();
    };
    LoginService.prototype.login = function () {
        this.oauthService.initImplicitFlow();
        console.log(this.oauthService.getAccessToken());
    };
    LoginService.prototype.logout = function () {
        this.oauthService.logOut();
    };
    LoginService.prototype.muleHttpOptions = function () {
        var accessToken = this.oauthService.getAccessToken();
        //console.log('accessToken: ', accessToken);
        return {
            headers: new HttpHeaders({
                'Authorization': 'Bearer ' + accessToken,
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            })
        };
    };
    LoginService.prototype.stripeHttpOptions = function () {
        var accessToken = this.oauthService.getAccessToken();
        //console.log('accessToken: ', accessToken);
        return {
            headers: new HttpHeaders({
                'Authorization': 'Bearer ' + accessToken,
                'Content-Type': 'application/json'
            })
        };
    };
    LoginService = __decorate([
        Injectable({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [OAuthService])
    ], LoginService);
    return LoginService;
}());
export { LoginService };
//# sourceMappingURL=login.service.js.map