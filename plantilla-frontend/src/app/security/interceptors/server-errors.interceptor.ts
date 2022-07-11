import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse } from '@angular/common/http';
import { Observable, EMPTY } from 'rxjs';
import { tap, catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';


@Injectable({
    providedIn: 'root'
})
export class ServerErrorsInterceptor implements HttpInterceptor {

    constructor(private snackBar: MatSnackBar, private authService: LoginService,
        private router: Router) {
          // private loader: AppLoaderService
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(retry(environment.REINTENTOS)).
            pipe(tap(event => {
                if (event instanceof HttpResponse) {
                    if (event.body && event.body.error === true && event.body.errorMessage) {
                        throw new Error(event.body.errorMessage);
                    }
                }
            })).pipe(catchError((err) => {

                // this.loader.cerrar();
                console.error(err);
                console.log("codigo ==="+err.status +" Mensaje==="+err.error.message);

                if (err.status == 0) {
                    this.snackBar.open('Error: No existe conexion con el servidor backend', 'ERROR SERVIDOR', { duration: 12000 });
                }
                else if (err.status === 501){ //error con mensaje personalizado logica de negocio
                    this.snackBar.open(err.error.message, 'ERROR 501', { duration: 12000 });
                }
                else if (err.status === 400) {
                   // this.snackBar.open(err.error.message, 'ERROR 400', { duration: 12000 }); //comente para pase a produccion
                   // this.snackBar.open("Error, Vuelva a intentarlo mas tarde", 'ERROR 400', { duration: 12000 }); // descomente pase a produccion
                   this.snackBar.open("Datos incorrectos", 'ERROR 400', { duration: 12000 }); // descomente pase a produccion
                }
                else if (err.status === 401) {  //No Autorizado

                    if (!this.authService.isAuthenticated()) {
                        this.authService.cerrarSesionExpirado();
                    }else{
                        this.snackBar.open(err.error.message, 'ERROR 401', { duration: 12000 });
                        this.router.navigate(['/dashboard']);
                    }
                }
                else if (err.status === 404) {  //No found
                    this.snackBar.open(err.error.message, 'ERROR 404', { duration: 12000 });
                }
                else if (err.status === 500) {
                   // this.snackBar.open(err.error.message, 'ERROR 500', { duration: 12000 });//comente para pase a produccion
                   this.snackBar.open("Error, Vuelva a intentarlo más tarde", 'ERROR 500', { duration: 12000 }); // descomente pase a produccion
                }
                else {
                    this.snackBar.open(err.error.message, 'ERROR 504', { duration: 12000 }); //comente para pase a produccion
                    // this.snackBar.open("Error, Vuelva a intentarlo mas tarde", 'ERROR 504', { duration: 12000 }); // descomente pase a produccion
                }
                return EMPTY;
            }));
    }
}
