import { TblUsuario } from './../models/tbl-usuario';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  url = `${environment.HOST}/usuarios`;

  private httpHeaders: HttpHeaders;

  constructor(private httpClient: HttpClient) {
    this.httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  }

  /**
   * Toma un objeto TblUsuario como parámetro, lo envía al backend y devuelve un objeto TblUsuario
   * @param {TblUsuario} tblUsuarioDTO - TblUsuario
   * @returns La respuesta se está devolviendo como un objeto con una clave de tblUsuarioDTOCreado.
   */
  crearUsuario(tblUsuarioDTO: TblUsuario): Observable<TblUsuario> {
    const urlEndPoint = `${this.url}/crearUsuario`;
    const clave = 'tblUsuarioDTOCreado';

    return this.httpClient.post<TblUsuario>(urlEndPoint, tblUsuarioDTO, { headers: this.httpHeaders })
      .pipe(
        map((respuesta: any) => respuesta[clave] as TblUsuario)
      );
  }

 /**
  * Toma un objeto TblUsuario como parámetro, lo envía al backend y devuelve un objeto TblUsuario
  * @param {TblUsuario} tblUsuarioDTO - TblUsuario
  * @returns La respuesta se está devolviendo como un objeto con una clave de tblUsuarioDTOCreado.
  */
  crearUsuarioExterno(tblUsuarioDTO: TblUsuario): Observable<TblUsuario> {
    const urlEndPoint = `${this.url}/crearUsuarioExterno`;
    const clave = 'tblUsuarioDTOCreado';

    return this.httpClient.post<TblUsuario>(urlEndPoint, tblUsuarioDTO, { headers: this.httpHeaders })
      .pipe(
        map((respuesta: any) => respuesta[clave] as TblUsuario)
      );
  }

  /**
   * Devuelve un observable de tipo any.
   * @param {number} idUsuario - número
   * @returns una matriz de objetos.
   */
  obtenerConfiguracionesGenerales(idUsuario: number): Observable<any> {
    const urlEndPoint = `${this.url}/obtenerConfiguracionesGenerales/${idUsuario}`;
    return this.httpClient.get<any[]>(urlEndPoint);
  }
}
