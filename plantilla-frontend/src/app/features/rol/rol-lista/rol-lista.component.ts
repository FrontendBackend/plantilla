import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { ParametroDialogo } from 'src/app/models/ParametroDialogo';
import { TblRolDTO } from 'src/app/models/TblRolDTO';
import { ETipoAccionCRUD } from 'src/app/models/tipo-accion';
import { RolService } from 'src/app/services/rol.service';
import { RolDialogoComponent } from '../rol-dialogo/rol-dialogo.component';

@Component({
  selector: 'app-rol-lista',
  templateUrl: './rol-lista.component.html',
  styleUrls: ['./rol-lista.component.scss']
})
export class RolListaComponent implements OnInit {

  @Input() tblRolDTO: TblRolDTO;

  @Input() tipoAccionCrud = ETipoAccionCRUD.NINGUNA;

  enProceso = false;

  cantidadRegistros: number;

  listarRol: TblRolDTO[];

  dataSourceRol: MatTableDataSource<TblRolDTO>;

  esFormularioSoloLectura = false;

  enProcesoModificacionConsorcio = false;

  @ViewChild('dataTable') table: MatTable<TblRolDTO>;

  constructor(
    private rolService: RolService,
    private matDialog: MatDialog,
    private matSnackBar: MatSnackBar,
  ) {
  }

  ngOnInit(): void {
    this.enProceso = true;

    if (this.tipoAccionCrud === ETipoAccionCRUD.CONSULTAR) {
      this.esFormularioSoloLectura = true;
    }
    this.listarRoles();
  }

  arrastrarSoltarItem(event: CdkDragDrop<TblRolDTO[]> | CdkDragDrop<any>): void {

    console.log({'event':event});
    const prevIndex = this.dataSourceRol.data.findIndex((d) =>
      d === event.item.data
    );

    moveItemInArray(this.dataSourceRol.data, prevIndex, event.currentIndex);

    let indicePrevio = prevIndex + 1;
    let indiceActual = event.currentIndex + 1;

    // console.log('Antes => ' + indicePrevio);
    //   console.log('Ahora => ' + indiceActual);

    if (indicePrevio !== indiceActual) {
      // Esta es una opción para actualizar el orden de la lista de manera automatizada
      this.dataSourceRol.data.forEach((rol, idx) => {
        rol.nuOrden = idx + 1;
        rol.nombre;

        this.actualizarOrden(rol.idRol, rol.nuOrden);
      });


      console.log('Antes => ' + indicePrevio);
      console.log('Ahora => ' + indiceActual);

      this.matSnackBar.open('Se ordenó el item seleccionado', 'Ok', {
        duration: 3000
      });
    }

    this.table.renderRows();
  }


  actualizarOrden(obtenerIdRol: number, indiceActual: number) {
    this.enProceso = true;

    let tblRolDTOCU = new TblRolDTO();
    tblRolDTOCU.nuOrden = indiceActual;

    // Recuperando las propiedades originales
    tblRolDTOCU.idRol = obtenerIdRol;

    this.rolService.modificarRol(tblRolDTOCU).subscribe(() => {
      this.listarRoles();
      this.enProceso = false;
    });
  }

  get columnasAMostrar(): string[] {
    let columnasAMostrar: string[];
    columnasAMostrar = [
      'nuOrden',
      'nombre',
      'descripcion',
      'acciones',
    ];

    return columnasAMostrar;
  }

  listarRoles(): void {

    this.enProceso = true;
    this.rolService.listarRoles().subscribe(respuesta => {
      this.dataSourceRol = new MatTableDataSource(respuesta);
      this.cantidadRegistros = this.dataSourceRol.data.length;

      setTimeout(() => {
        this.enProceso = false;
      });
    });
  }

  crearRol() {
    let tblRolDto: TblRolDTO;

    const parametroDialogo = new ParametroDialogo<TblRolDTO, any>();
    parametroDialogo.accion = ETipoAccionCRUD.CREAR;
    parametroDialogo.objeto = new TblRolDTO();
    parametroDialogo.objeto.idRol = 0;

    const dialogRef = this.matDialog.open(RolDialogoComponent, {
      disableClose: true,
      data: parametroDialogo
    });

    dialogRef.afterClosed().subscribe(resultado => {
      if (parametroDialogo.resultado === 'ok') {
        tblRolDto = parametroDialogo.objeto;
        this.listarRoles();
      }
    });
  }

  modificarRol(tblRolDto: TblRolDTO, indice: number) {

    const parametroDialogo = new ParametroDialogo<TblRolDTO, any>();
    parametroDialogo.accion = ETipoAccionCRUD.MODIFICAR;
    parametroDialogo.objeto = tblRolDto;

    const dialogo = this.matDialog.open(RolDialogoComponent, {
      disableClose: true,
      data: parametroDialogo
    });

    dialogo.afterClosed().subscribe(resultado => {
      if (parametroDialogo.resultado === 'ok') {
        tblRolDto = parametroDialogo.objeto;
        this.listarRoles();
      }
    });

  }

  consultarRol(tblRolDto: TblRolDTO, indice: number) {

    const parametroDialogo = new ParametroDialogo<TblRolDTO, any>();
    parametroDialogo.accion = ETipoAccionCRUD.CONSULTAR;
    parametroDialogo.objeto = tblRolDto;

    const dialogo = this.matDialog.open(RolDialogoComponent, {
      disableClose: true,
      data: parametroDialogo
    });

    dialogo.afterClosed().subscribe(resultado => {
      // Nada que hacer.
    });

  }
}
