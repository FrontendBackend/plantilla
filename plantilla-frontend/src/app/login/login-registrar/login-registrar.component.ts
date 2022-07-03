import { MatSnackBar } from '@angular/material/snack-bar';
import { TblUsuario } from './../../models/tbl-usuario';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AbstractControl, FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { ETipoAccionCRUD } from 'src/app/models/tipo-accion';
import { UsuarioService } from 'src/app/services/usuario.service';
import { PasswordValidation } from 'src/app/models/match';

@Component({
  selector: 'app-login-registrar',
  templateUrl: './login-registrar.component.html',
  styleUrls: ['./login-registrar.component.scss']
})
export class LoginRegistrarComponent implements OnInit {

  form: FormGroup = new FormGroup({});

  @Input() tblUsuarioDTO: TblUsuario;

  lTblUsuarioDTO: TblUsuario[];

  enProceso = false;

  esFormularioSoloLectura = false;

  controlesNovalidos: any[];

  @Input() tipoAccionCrud = ETipoAccionCRUD.NINGUNA;

  @Output() eventoUsuarioCreado = new EventEmitter<TblUsuario>();

  constructor(
    private fb: FormBuilder,
    private snack: MatSnackBar,
    private usuarioService: UsuarioService
  ) {
  }


  ngOnInit() {

    this.inicializarFormulario();
  }

  inicializarFormulario(): void {
    this.form = this.fb.group(
      {
        username: [null],
        correo: [null],
        password: [null],
        confirmarPassword: [null],
      },
      {
        validator: PasswordValidation.MatchPassword
      }
    );

    this.iniciarFormulario();
  }

  configurarInicio() {
    this.enProceso = true;

    let idUsuario = 0;

    if (this.tblUsuarioDTO.idUsuario) {
      idUsuario = this.tblUsuarioDTO.idUsuario;
    }

    this.usuarioService.obtenerConfiguracionesGenerales(idUsuario).subscribe((respuesta: any) => {
      const claveSustancia = 'tblUsuarioDTO';

      this.lTblUsuarioDTO = respuesta[claveSustancia];


      this.iniciarFormulario();
      this.enProceso = false;
    });
  }

  iniciarFormulario(): void {
    this.enProceso = true;
    if (this.tipoAccionCrud === ETipoAccionCRUD.CONSULTAR) {
      this.esFormularioSoloLectura = true;
    }

    this.form = this.fb.group({
      username: [this.tblUsuarioDTO.username, {
        asyncValidators: [this.usuarioService.noUsuarioValidator(this.tblUsuarioDTO.idUsuario)],
        updateOn: 'change'
        // updateOn: 'blur'
      }],
      correo: [this.tblUsuarioDTO.correo, {
        asyncValidators: [this.usuarioService.correoValidator(this.tblUsuarioDTO.idUsuario)],
        updateOn: 'change'
        // updateOn: 'blur'
      }],
      password: ['', [Validators.required]],
      confirmarPassword: [this.tblUsuarioDTO.password, [Validators.required]],
    },
      {
        validator: PasswordValidation.MatchPassword
      }
    );

    this.form.controls.username.setValidators([
      Validators.required
    ]);

    this.form.controls.correo.setValidators([
      Validators.required,
      this.esEmailValidoCorreo.bind(this)
    ]);

    if (this.esFormularioSoloLectura) {
      Object.values(this.form.controls).forEach(control => control.disable());
    }
    this.enProceso = false;
  }


  esEmailValidoCorreo(email: AbstractControl): { [key: string]: boolean } {
    if (email !== null && email.value !== null && email.value !== '' && email.value !== undefined) {
      const EMAIL_REGEX = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
      if (email.value.match(EMAIL_REGEX)) {
        return null;
      } else {
        return { descDeCorreoIncorrecto: true };
      }
    } else {
      return null;
    }
  }

  tieneError(campo: string, error: string): boolean {
    if (error === 'any' || error === '') {
      return (
        this.form.get(campo).invalid &&
        this.form.get(campo).touched
      );
    }

    return (
      this.form.get(campo).hasError(error)
    );
  }

  /**
   * Procesa el envío del formulario.
   */
  enviar() {

    this.buscarControlesNoValidos();

    if (this.form.invalid) {

      Object.values(this.form.controls).forEach(control => control.markAllAsTouched());

      this.snack.open('Existen datos incorrectos o faltantes, por favor verifique', 'CERRAR', {
        duration: 4000,
        // panelClass: ['mat-toolbar', 'mat-primary']
      });

      return;
    }

    this.procesarCrearOModificar();
  }

  public buscarControlesNoValidos() {
    this.controlesNovalidos = [];
    const controls = this.form.controls;
    for (const name in controls) {
      if (controls[name].invalid) {
        this.controlesNovalidos.push({ control: name, errores: controls[name].errors });
      }
    }
  }

  /**
   * Permite procesar la acción de crear o modificar.
   */
  procesarCrearOModificar() {
    this.enProceso = true;

    let tblUsuarioDTOCU = new TblUsuario();
    tblUsuarioDTOCU = this.form.value;

    // Recuperando las propiedades originales
    tblUsuarioDTOCU.idUsuario = this.tblUsuarioDTO.idUsuario;

    let peticion: Observable<TblUsuario>;
    let mensaje: string;

    switch (+this.tipoAccionCrud) {
      case ETipoAccionCRUD.CREAR:
        peticion = this.usuarioService.crearUsuarioExterno(tblUsuarioDTOCU);
        mensaje = 'El usuario ha sido creada';
        break;
      default:
        console.log('Ninguna acción implementada');
        break;
    }

    if (peticion) {
      peticion.subscribe(respuesta => {
        this.snack.open(mensaje, 'Ok', {
          duration: 4000
        });

        this.tblUsuarioDTO = respuesta;
        if (this.tipoAccionCrud === ETipoAccionCRUD.CREAR) {
          this.eventoUsuarioCreado.emit(this.tblUsuarioDTO);
        }
      });
    }
  }
}
