import { MatSnackBar } from '@angular/material/snack-bar';
import { TblUsuario } from './../../models/tbl-usuario';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
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
  token: string;
  mensaje: string;
  error: string;
  rpta: number;
  tokenValido: boolean;

  @Input() tblUsuarioDTO: TblUsuario;

  lTblUsuarioDTO: TblUsuario[];

  enProceso = false;

  esFormularioSoloLectura = false;

  controlesNovalidos: any[];

  @Input() tipoAccionCrud = ETipoAccionCRUD.NINGUNA;

  @Output() eventoUsuarioCreado = new EventEmitter<TblUsuario>();

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snack: MatSnackBar,
    private route: ActivatedRoute,
    private usuarioService: UsuarioService
  ) { }


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

    if (this.tipoAccionCrud === ETipoAccionCRUD.CONSULTAR) {
      this.esFormularioSoloLectura = true;
    }

    this.form = this.fb.group({
      username: [this.tblUsuarioDTO.username, [Validators.required]],
      correo: [this.tblUsuarioDTO.correo, [Validators.required]],
      password: ['', [Validators.required]],
      confirmarPassword: [this.tblUsuarioDTO.password, [Validators.required]],
    },
      {
        validator: PasswordValidation.MatchPassword
      }
    );

    if (this.esFormularioSoloLectura) {
      Object.values(this.form.controls).forEach(control => control.disable());
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
