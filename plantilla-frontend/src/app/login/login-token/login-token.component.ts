import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { PasswordValidation } from 'src/app/models/match';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login-token',
  templateUrl: './login-token.component.html',
  styleUrls: ['./login-token.component.scss']
})
export class LoginTokenComponent implements OnInit {

  form: FormGroup;
  token: string;
  rpta: number;
  tokenValido: boolean;

  enProceso = false;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private loginService: LoginService,
    private snack: MatSnackBar,
  ) {
  }


  ngOnInit() {
    this.form = this.fb.group({
      password: [''],
      confirmarPassword: ['']
    }, {
      validator: PasswordValidation.MatchPassword
    });

    this.enProceso = true;
    this.route.params.subscribe((params: Params) => {
      this.token = params['token'];
      this.loginService.verificarTokenReset(this.token).subscribe(data => {

        if (data === 1) {
          this.tokenValido = true;
          this.enProceso = false;
        } else {
          this.tokenValido = false;
          setTimeout(() => {
            this.enProceso = false;
            this.router.navigate(['login']);
          }, 7000)
        }
      });
    })
  }

  onSubmit() {
    this.enProceso = true;
    let clave: string = this.form.value.confirmarPassword;
    this.loginService.restablecer(this.token, clave).subscribe(data => {

      let mensaje = 'Se cambio la contraseña';
      setTimeout(() => {
        this.snack.open(mensaje, 'Ok', {
          duration: 4000
        });

        this.enProceso = true;
        this.router.navigate(['login']);
      }, 2000);

    });
  }

}
