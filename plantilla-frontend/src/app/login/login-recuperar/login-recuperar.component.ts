import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login-recuperar',
  templateUrl: './login-recuperar.component.html',
  styleUrls: ['./login-recuperar.component.scss']
})
export class LoginRecuperarComponent implements OnInit {

  email: string;
  mensaje: string;
  error: string;
  porcentaje: number = 0;

  constructor(
    private loginService: LoginService,
    public route: ActivatedRoute,
    private router: Router,
    private snack: MatSnackBar,
  ) { }

  ngOnInit(): void {
  }

  enviar() {
    this.porcentaje = 20;
    this.loginService.enviarCorreo(this.email).subscribe(data => {
      if (data === 1) {
        // this.error = null
        this.porcentaje = 100;
        let mensaje = "Se enviaron las indicaciones al correo."
        this.snack.open(mensaje, 'Ok', {
          duration: 4000
        });
        this.router.navigate(['/login']);
      } else {
        let error = "El usuario ingresado no existe";
        this.snack.open(error, 'Error', {
          duration: 4000
        });
        this.porcentaje = 0;
      }
    });
  }
}
