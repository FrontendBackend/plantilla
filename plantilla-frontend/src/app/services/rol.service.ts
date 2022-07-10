import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TblRolDTO } from '../models/TblRolDTO';

@Injectable({
  providedIn: 'root'
})
export class RolService {

  url = `${environment.HOST}/roles`;

  private httpHeaders: HttpHeaders;

  constructor(private httpClient: HttpClient) {
    this.httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  }

  /**
   * Devuelve una lista de roles
   * @returns
   */
  listarRoles(): Observable<TblRolDTO[]> {
    const endPoint = `${this.url}/listarRoles`;
    return this.httpClient.get<TblRolDTO[]>(endPoint, { headers: this.httpHeaders });
  }

  /**
   * Me permite crear nuevos roles
   * @param tblRolDTO
   * @returns
   */
  crearRol(tblRolDTO: TblRolDTO): Observable<TblRolDTO> {
    const urlEndPoint = `${this.url}/crearRol`;
    const clave = 'tblRolDTOCreado';

    return this.httpClient.post<TblRolDTO>(urlEndPoint, tblRolDTO, { headers: this.httpHeaders })
      .pipe(
        map((respuesta: any) => respuesta[clave] as TblRolDTO)
      );
  }

  /**
   * Me permite modificar un rol
   * @param tblRolDTO
   * @returns
   */
  modificarRol(tblRolDTO: TblRolDTO): Observable<TblRolDTO> {
    const urlEndPoint = `${this.url}/modificarRol`;

    return this.httpClient.put<TblRolDTO>(urlEndPoint, tblRolDTO, { headers: this.httpHeaders })
      .pipe(
        map((respuesta: any) => respuesta['tblRolDTOModificada'] as TblRolDTO)
      );
  }
}
