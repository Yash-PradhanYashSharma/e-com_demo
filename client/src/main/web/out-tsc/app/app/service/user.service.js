import { __decorate, __metadata } from "tslib";
import { Injectable } from '@angular/core';
import { OAuthService } from "angular-oauth2-oidc";
var UserService = /** @class */ (function () {
    function UserService(oauthService) {
        this.oauthService = oauthService;
    }
    UserService.prototype.isUserLoggedIn = function () {
        return this.userDetails() != null;
    };
    UserService.prototype.userDetails = function () {
        var claims = this.oauthService.getIdentityClaims();
        if (!claims) {
            return null;
        }
        //console.log('claims', claims);
        this.name = claims['name'];
        this.id = claims['preferred_username'];
        return claims['name'];
    };
    UserService = __decorate([
        Injectable({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [OAuthService])
    ], UserService);
    return UserService;
}());
export { UserService };
//# sourceMappingURL=user.service.js.map