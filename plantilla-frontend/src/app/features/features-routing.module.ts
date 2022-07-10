import { AuditoriaListaComponent } from './auditoria/auditoria-lista/auditoria-lista.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuditoriaContenedorDetalleComponent } from './auditoria/auditoria-contenedor-detalle/auditoria-contenedor-detalle.component';
import { UsuarioContenedorDetalleComponent } from './usuario/usuario-contenedor-detalle/usuario-contenedor-detalle.component';
import { UsuarioListaComponent } from './usuario/usuario-lista/usuario-lista.component';
import { RolContenedorDetalleComponent } from './rol/rol-contenedor-detalle/rol-contenedor-detalle.component';
import { RolListaComponent } from './rol/rol-lista/rol-lista.component';

const routes: Routes = [

  {
    path: 'auditoria',
    component: AuditoriaContenedorDetalleComponent,
    data: {
      title: 'Auditoria general',
      breadcrumb: 'Auditoria general'
    },
    children: [
      {
        path: '',
        component: AuditoriaListaComponent,
        data: {
          title: 'Auditoria para lista general',
          breadcrumb: 'Auditoria para lista general'
        }
      },
    ]
  },
  {
    path: 'usuario',
    component: UsuarioContenedorDetalleComponent,
    data: {
      title: 'Usuario',
      breadcrumb: 'Usuario'
    },
    children: [
      {
        path: '',
        component: UsuarioListaComponent,
        data: {
          title: 'Lista de Usuarios',
          breadcrumb: 'Lista de Usuarios'
        }
      },
    ]
  },
  {
    path: 'rol',
    component: RolContenedorDetalleComponent,
    data: {
      title: 'Rol',
      breadcrumb: 'Rol'
    },
    children: [
      {
        path: '',
        component: RolListaComponent,
        data: {
          title: 'Lista de Roles',
          breadcrumb: 'Lista de Roles'
        }
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FeaturesRoutingModule { }
