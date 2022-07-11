import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit, Inject, Input, Output, EventEmitter } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { TblRolDTO } from 'src/app/models/TblRolDTO';
import { ETipoAccionCRUD } from 'src/app/models/tipo-accion';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RolService } from 'src/app/services/rol.service';
import { ParametroDialogo } from 'src/app/models/ParametroDialogo';

@Component({
  selector: 'app-rol-dialogo',
  templateUrl: './rol-dialogo.component.html',
  styleUrls: ['./rol-dialogo.component.scss']
})
export class RolDialogoComponent implements OnInit {

  @Input() tblRolDTO: TblRolDTO;

  @Input() tipoAccionCrud = ETipoAccionCRUD.NINGUNA;

  enProceso = false;

  controlesNovalidos: any[];

  esFormularioSoloLectura = false;

  frmReactivo: FormGroup;

  nombreAccion: string;

  constructor(private formBuilder: FormBuilder,
    private matSnackBar: MatSnackBar,
    private rolService: RolService,
    @Inject(MAT_DIALOG_DATA)
    public parametroDialogo: ParametroDialogo<TblRolDTO, any>,
    private dialogRef: MatDialogRef<RolDialogoComponent>
  ) {
  }

  ngOnInit(): void {
    this.tblRolDTO = this.parametroDialogo.objeto;

    this.nombreAccion = ETipoAccionCRUD[this.parametroDialogo.accion];
    if (this.parametroDialogo.accion === ETipoAccionCRUD.CONSULTAR) {
      this.esFormularioSoloLectura = true;
    }

    this.inicializarFormulario();
  }

  inicializarFormulario(): void {
    this.frmReactivo = this.formBuilder.group(
      {
        nuOrden: [null],
        nombre: [null],
        descripcion: [null],
      }
    );
    this.dialogRef.updateSize('38%');

    this.configurarInicio();
  }

  configurarInicio() {
    this.enProceso = true;

    const idRol = this.tblRolDTO.idRol;

    this.rolService.obtenerConfiguracionesGenerales(idRol).subscribe((respuesta: any) => {
      const clave = 'tblPortafolioDTO';

      if (this.tipoAccionCrud === ETipoAccionCRUD.MODIFICAR || this.tipoAccionCrud === ETipoAccionCRUD.CONSULTAR) {
        this.tblRolDTO = respuesta[clave];
      }

      this.iniciarFormulario();

      this.enProceso = false;
    });
  }

  iniciarFormulario() {

    if (this.tipoAccionCrud === ETipoAccionCRUD.CONSULTAR) {
      this.esFormularioSoloLectura = true;
    }

    this.frmReactivo = this.formBuilder.group(
      {
        nuOrden: [this.tblRolDTO.nuOrden, [Validators.required]],
        nombre: [this.tblRolDTO.nombre, [Validators.required]],
        descripcion: [this.tblRolDTO.descripcion, [Validators.required]],
      }
    );

    if (this.esFormularioSoloLectura) {
      Object.values(this.frmReactivo.controls).forEach(control => control.disable());
    }
  }

  get f() { return this.frmReactivo.controls; }


  /**
   * Permite verificar si el control dado por el nombre tiene algún tipo de error.
   * @param campo Nombre del control.
   * @param error Error buscado.
   */
  tieneError(campo: string, error: string): boolean {
    if (error === 'any' || error === '') {
      return (
        this.frmReactivo.get(campo).invalid
      );
    }

    return (
      this.frmReactivo.get(campo).hasError(error)
    );
  }

  /**
  * Permite crear la empresa supervisora.
  */
  enviar() {
    // this.buscarControlesNoValidos();
    if (this.frmReactivo.invalid) {
      Object.values(this.frmReactivo.controls).forEach(control => control.markAllAsTouched());

      this.matSnackBar.open('Existen datos incorrectos o faltantes, por favor verifique', 'CERRAR', {
        duration: 4000
      });
      return;
    }

    this.procesarCrearOModificar();
  }

  /**
   * Permite procesar la acción de crear o modificar un contrato.
   */
  procesarCrearOModificar() {
    this.enProceso = true;

    let tblRolDTOCU = new TblRolDTO();
    tblRolDTOCU = this.frmReactivo.value;

    // Recuperando las propiedades originales
    tblRolDTOCU.idRol = this.tblRolDTO.idRol;


    let peticion: Observable<TblRolDTO>;
    let mensaje: string;

    switch (+this.parametroDialogo.accion) {
      case ETipoAccionCRUD.CREAR:
        peticion = this.rolService.crearRol(tblRolDTOCU);
        mensaje = 'El rol ha sido creado';
        break;
      case ETipoAccionCRUD.MODIFICAR:
        peticion = this.rolService.modificarRol(tblRolDTOCU);
        mensaje = 'El rol ha sido modififcado';
        break;
      default:
        console.log('Ninguna acción implementada');
        break;
    }

    if (peticion) {
      peticion.subscribe((respuesta: any) => {
        this.matSnackBar.open(mensaje, 'Ok', {
          duration: 3000
        });

        this.parametroDialogo.objeto = respuesta;
        this.parametroDialogo.resultado = 'ok';

        this.enProceso = false;
        this.dialogRef.close();
      }
      );
    }
  }

  public buscarControlesNoValidos() {
    this.controlesNovalidos = [];
    const controls = this.frmReactivo.controls;
    for (const name in controls) {
      if (controls[name].invalid) {
        this.controlesNovalidos.push({ control: name, errores: controls[name].errors });
      }
    }
  }

}
