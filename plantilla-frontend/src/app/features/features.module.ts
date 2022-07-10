import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FeaturesRoutingModule } from './features-routing.module';
import { AuditoriaListaComponent } from './auditoria/auditoria-lista/auditoria-lista.component';
import { AuditoriaTarjetaComponent } from './auditoria/auditoria-tarjeta/auditoria-tarjeta.component';
import { UsuarioListaComponent } from './usuario/usuario-lista/usuario-lista.component';
import { UsuarioTarjetaComponent } from './usuario/usuario-tarjeta/usuario-tarjeta.component';
import { UsuarioDialogoComponent } from './usuario/usuario-dialogo/usuario-dialogo.component';
import { AuditoriaContenedorDetalleComponent } from './auditoria/auditoria-contenedor-detalle/auditoria-contenedor-detalle.component';
import { DemoMaterialModule } from '../demo-material-module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CdkTableModule } from '@angular/cdk/table';
import { Not404Component } from './error/not404/not404.component';
import { Not403Component } from './error/not403/not403.component';
import { UsuarioContenedorDetalleComponent } from './usuario/usuario-contenedor-detalle/usuario-contenedor-detalle.component';
import { RolListaComponent } from './rol/rol-lista/rol-lista.component';
import { RolDialogoComponent } from './rol/rol-dialogo/rol-dialogo.component';
import { RolContenedorDetalleComponent } from './rol/rol-contenedor-detalle/rol-contenedor-detalle.component';
import { DragDropModule } from '@angular/cdk/drag-drop';

@NgModule({
  declarations: [
    AuditoriaListaComponent,
    AuditoriaTarjetaComponent,
    UsuarioListaComponent,
    UsuarioTarjetaComponent,
    UsuarioDialogoComponent,
    AuditoriaContenedorDetalleComponent,
    Not404Component,
    Not403Component,
    UsuarioContenedorDetalleComponent,
    RolListaComponent,
    RolDialogoComponent,
    RolContenedorDetalleComponent
  ],
  imports: [
    CommonModule,
    FeaturesRoutingModule,
    DemoMaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    CdkTableModule,
    DragDropModule,
  ]
})
export class FeaturesModule { }
