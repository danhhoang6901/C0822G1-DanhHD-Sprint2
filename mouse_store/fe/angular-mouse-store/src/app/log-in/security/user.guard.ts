import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {TokenService} from "../../service/token.service";

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {
  constructor(private token: TokenService) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.token.getRole() == 'ROLE_CUSTOMER') {
      return true;
    }
    return false;
  }

}
