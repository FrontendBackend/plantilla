import { Router } from '@angular/router';
import { Component, OnInit, Input } from '@angular/core';
import { ETipoAccionCRUD } from 'src/app/models/tipo-accion';
import { TblUsuario } from 'src/app/models/TblUsuarioDTO';

@Component({
  selector: 'app-usuario-tarjeta',
  templateUrl: './usuario-tarjeta.component.html',
  styleUrls: ['./usuario-tarjeta.component.scss']
})
export class UsuarioTarjetaComponent implements OnInit {

  @Input() tblUsuarioDTO: TblUsuario;

  constructor(
    private router: Router,
    // public authService: AuthService
  ) { }

  ngOnInit(): void {
  }

  /**
   * Permite iniciar la edición de una matriz.
   * @param idMatriz Identificador de matriz a editar.
   */
  editarMatriz(idMatriz: number) {
    this.router.navigate([`/info-maestra/matriz/${ETipoAccionCRUD.MODIFICAR}`, idMatriz, 0]);
  }

  /**
   * Permite abrir el formulario de la matriz en solo lectura.
   * @param idMatriz Identificador de matriz a consultar.
   */
  consultarMatriz(idMatriz: number) {
    this.router.navigate([`/info-maestra/matriz/${ETipoAccionCRUD.CONSULTAR}`, idMatriz, 0]);
  }

}
