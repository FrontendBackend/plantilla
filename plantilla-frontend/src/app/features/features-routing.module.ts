import { AuditoriaListaComponent } from './auditoria/auditoria-lista/auditoria-lista.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuditoriaContenedorDetalleComponent } from './auditoria/auditoria-contenedor-detalle/auditoria-contenedor-detalle.component';

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
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FeaturesRoutingModule { }
