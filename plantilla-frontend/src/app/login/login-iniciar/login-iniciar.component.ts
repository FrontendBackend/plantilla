import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login-iniciar',
  templateUrl: './login-iniciar.component.html',
  styleUrls: ['./login-iniciar.component.scss']
})
export class LoginIniciarComponent implements OnInit {

  usuario: string;

  clave: string;

  mensaje: string;

  error: string;

  enProceso = false;

  constructor(
    private loginService: LoginService,
    private router: Router,
    private snack: MatSnackBar,
  ) { }

  ngOnInit(): void {

  }

  /**
   * La función iniciarSesion() se llama cuando el usuario hace clic en el botón "Iniciar Sesión" en la
   * página de inicio de sesión
   */
  iniciarSesion() {

    this.enProceso = true;

    if (this.usuario === undefined && this.clave === undefined) {
      this.snack.open('Complete ambos campos', 'CERRAR', {
        duration: 4000
      });
      this.enProceso = false;
    }

    else {
      this.loginService.login(this.usuario, this.clave).subscribe((data: any) => {

        sessionStorage.setItem(environment.TOKEN_NAME, data.access_token);

        this.loginService.guardarUsuario(data.access_token);

        this.loginService.guardarToken(data.access_token);

        this.router.navigate(['dashboard']);

        this.snack.open(`Bienvenido ${this.usuario}`, 'CERRAR', {
          duration: 12000
        });

        this.enProceso = false;
      });
    }
  }
}
