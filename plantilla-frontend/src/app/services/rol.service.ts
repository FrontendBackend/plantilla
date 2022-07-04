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
}
