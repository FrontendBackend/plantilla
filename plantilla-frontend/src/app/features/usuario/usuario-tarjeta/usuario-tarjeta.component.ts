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
    
    // console.log(this.router.routerState.snapshot.url);
    // console.log({'': this.router.routerState});
    if(this.router.routerState.snapshot.url === "/usuario"){
      this.router.navigate([`/not-404-interno`]);
    }else{
      this.router.navigate([`/info-maestra/matriz/${ETipoAccionCRUD.MODIFICAR}`, idMatriz, 0]);
    }
  }

  /**
   * Permite abrir el formulario de la matriz en solo lectura.
   * @param idMatriz Identificador de matriz a consultar.
   */
  consultarMatriz(idMatriz: number) {

    // Si no esta definido la ruta, marcará como "Página no encontrado", de lo contrario, la ruta está definido.
    if(this.router.routerState.snapshot.url === "/usuario"){
      this.router.navigate([`/not-404-interno`]);
    }else{
      this.router.navigate([`/info-maestra/matriz/${ETipoAccionCRUD.CONSULTAR}`, idMatriz, 0]);
    }
  }

}
