import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from 'src/environments/environment';
import { LoginService } from './login.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GuardServiceService implements CanActivate {

  TOKEN_NAME_SESSION: string = 'access_token';

  constructor(
    private loginService: LoginService,
    // private menuService: MenuService,
    private router: Router
  ) { }

  // canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
  //   //1) VERIFICAR SI ESTA LOGUEADO
  //   let rpta = this.loginService.estaLogueado();
  //   if (!rpta) {
  //     this.loginService.cerrarSesion();
  //     sessionStorage.clear();
  //     sessionStorage.removeItem('token');
  //     sessionStorage.removeItem('usuario');
  //     this.router.navigate(['/login']);
  //     return false;
  //   } else {

  //     //2) VERIFICAR SI EL TOKEN NO HA EXPIRADO
  //     const helper = new JwtHelperService();
  //     let token = sessionStorage.getItem(environment.TOKEN_NAME);
  //     if (!helper.isTokenExpired(token)) {

  //       //3) VERIFICAR SI TIENES EL ROL NECESARIO PARA ACCEDER A ESA PAGINA
  //       let url = state.url;
  //       const decodedToken = helper.decodeToken(token);
  //       return this.menuService.listarPorUsuario(decodedToken.user_name).pipe(map((data: LegMenu[]) => {
  //         this.menuService.setMenuCambio(data);

  //         let cont = 0;
  //         for (let m of data) {
  //           if (url.startsWith(m.url)) {
  //             cont++;
  //             break;
  //           }
  //         }

  //         if (cont > 0) {
  //           return true;
  //         } else {
  //           this.router.navigate(['dashboard/not-403']);
  //           return false;
  //         }

  //       }));

  //     } else {
  //       this.loginService.cerrarSesion();
  //       sessionStorage.clear();
  //       sessionStorage.removeItem('token');
  //       sessionStorage.removeItem('usuario');
  //       this.router.navigate(['/login']);
  //       return false;
  //     }
  //   }
  // }

  /**
   * Si el usuario no está autenticado, cierre la sesión y devuelva falso
   * @param {ActivatedRouteSnapshot} next - ActivatedRouteSnapshot: la ruta que se está activando.
   * @param {RouterStateSnapshot} state - Instantánea del estado del enrutador
   * @returns Un valor booleano.
   */
  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if (!this.loginService.isAuthenticated()) {
      this.loginService.cerrarSesion();
      return false;
    }
    return true;
  }
}
