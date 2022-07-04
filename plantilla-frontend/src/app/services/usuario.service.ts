import { TblUsuario } from '../models/TblUsuarioDTO';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AbstractControl, AsyncValidatorFn, ValidationErrors } from '@angular/forms';

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
   * Devuelve un Observable de una matriz de objetos TblUsuario
   * @returns Una matriz de objetos TblUsuario.
   */
  listarUsuarios(): Observable<TblUsuario[]> {
    const endPoint = `${this.url}/listarUsuarios`;
    return this.httpClient.get<TblUsuario[]>(endPoint, { headers: this.httpHeaders });
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
  obtenerUsuarioPorId(idUsuario: number): Observable<any> {
    const urlEndPoint = `${this.url}/obtenerUsuarioPorId/${idUsuario}`;
    return this.httpClient.get<any[]>(urlEndPoint);
  }

  /**
   * Me devuelve la lista de configuraciones en la cual esta incorporado los roles y otros
   * @returns
   */
  obtenerConfiguracionesGenerales(): Observable<any> {
    const urlEndPoint = `${this.url}/obtenerConfiguracionesGenerales`;
    return this.httpClient.get<any[]>(urlEndPoint);
  }

  /**
   * Me permite validar la existencia de un usuario ya registrado
   * @param idUsuario
   */
  noUsuarioValidator(idUsuario: number): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      const username = control.value;

      if (!username) {
        return of(null);
      }

      return this.yaExisteUsuario(idUsuario, username).pipe(
        map(respuesta => {
          const claveLista = 'lTblUsuarioDTO';
          const lTblUsuarioDTO = respuesta[claveLista] as TblUsuario[];

          if (lTblUsuarioDTO.length > 0) {
            return { usernameExiste: true };
          }
          else {
            return null;
          }
        })
      );
    };
  }

  /**
   * Me permite validar la existencia de un usuario ya registrado
   * @param idUsuario
   */
  yaExisteUsuario(idUsuario: number, username: string): Observable<any> {
    const urlEndPoint = `${this.url}/existeUsuario/${idUsuario}/${username}`;

    return this.httpClient.get<any[]>(urlEndPoint);
  }

  /**
   * Me permite validar la existencia de los correos ya registrados
   * @param idUsuario
   */
  correoValidator(idUsuario: number): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      const correo = control.value;

      if (!correo) {
        return of(null);
      }

      return this.yaExisteCorreo(idUsuario, correo).pipe(
        map(respuesta => {
          const claveLista = 'lTblUsuarioDTO';
          const lTblUsuarioDTO = respuesta[claveLista] as TblUsuario[];

          if (lTblUsuarioDTO.length > 0) {
            return { correoExiste: true };
          }
          else {
            return null;
          }
        })
      );
    };
  }

  /**
   * Me permite validar la existencia de los correos ya registrados
   * @param idUsuario
   */
  yaExisteCorreo(idUsuario: number, correo: string): Observable<any> {
    const urlEndPoint = `${this.url}/existeCorreo/${idUsuario}/${correo}`;

    return this.httpClient.get<any[]>(urlEndPoint);
  }
}
