import { UsuarioService } from './../../services/usuario.service';
import { TblUsuario } from './../../models/tbl-usuario';
import { Component, OnInit } from '@angular/core';
import { ETipoAccionCRUD } from 'src/app/models/tipo-accion';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login-formulario',
  templateUrl: './login-formulario.component.html',
  styleUrls: ['./login-formulario.component.scss']
})
export class LoginFormularioComponent implements OnInit {


  tblUsuarioDTO: TblUsuario;

  tipoAccionCrud = ETipoAccionCRUD.NINGUNA;

  textoRetorno = 'Retornar';

  pestaniaSeleccionada = 0;

  enProceso: boolean;

  constructor(private router: Router,
    private actividateRoute: ActivatedRoute) {
    this.enProceso = false;
  }

  ngOnInit(): void {
    this.enProceso = true;

    this.actividateRoute.params.subscribe((respuesta: any) => {

      const accion = this.actividateRoute.snapshot.url[0].path;

      if (accion === ETipoAccionCRUD.CREAR.toString()) {
        this.tipoAccionCrud = ETipoAccionCRUD.CREAR;
        this.tblUsuarioDTO = new TblUsuario();
        this.tblUsuarioDTO.idUsuario = 0;
        this.enProceso = false;
      }
    });
  }

  eventoUsuarioCreado() {
    this.router.navigate(['/login']);
  }
}
