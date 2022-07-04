import { TblUsuario } from '../../../models/TblUsuarioDTO';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ETipoAccionCRUD } from 'src/app/models/tipo-accion';
import { plantillaAnimations } from 'src/app/shared/animations/plantilla-animations';

@Component({
  selector: 'app-usuario-lista',
  templateUrl: './usuario-lista.component.html',
  styleUrls: ['./usuario-lista.component.scss'],
  animations: [plantillaAnimations],
})
export class UsuarioListaComponent implements OnInit {

  @ViewChild('txtBuscar', { static: true }) txtBuscar: ElementRef;

  /**
   * Señala si la carga de la lista se encuentra en curso.
   */
  enProceso = false;

  lTblUsuarioDTO: TblUsuario[];

  cantidadRegistros = 0;
  /**
   * Lista de tipos de normas.
   */
  constructor(private router: Router,
    private usuarioService: UsuarioService) {
  }

  ngOnInit(): void {
    this.listarUsuarios();
  }

  listarUsuarios() {

    this.enProceso = true;

    this.usuarioService.listarUsuarios().subscribe((respuesta) => {
      this.lTblUsuarioDTO = respuesta;
      this.cantidadRegistros = respuesta.length;

      this.enProceso = false;
    });

  }

  /**
   * Permite iniciar la edición de una matriz.
   * @param idMatriz Identificador de matriz a editar.
   */
  editarMatriz(idMatriz: number) {
    this.router.navigate([`/features/usuario/${ETipoAccionCRUD.MODIFICAR}`, idMatriz, 0]);
  }

  /**
   * Permite abrir el formulario de la matriz en solo lectura.
   * @param idMatriz Identificador de matriz a consultar.
   */
  consultarMatriz(idMatriz: number) {
    this.router.navigate([`/features/usuario/${ETipoAccionCRUD.CONSULTAR}`, idMatriz, 0]);
  }

}
