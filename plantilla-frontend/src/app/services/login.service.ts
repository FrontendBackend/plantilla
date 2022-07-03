import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { TblUsuario } from '../models/tbl-usuario';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private _usuario: TblUsuario;
  private _token: string;

  /* Configuración de la URL para el host y el punto final de oauth/token. */
  private url: string = `${environment.HOST}/oauth/token`

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  /**
   * Estamos enviando una solicitud POST a la URL especificada en el archivo de entorno, siendo el
   * cuerpo de la solicitud el nombre de usuario y la contraseña del usuario, codificados en el formato
   * especificado en los encabezados.
   * @param {string} usuario - El nombre de usuario del usuario.
   * @param {string} contrasena - cuerda
   * @returns la ficha
   */
  login(usuario: string, contrasena: string) {
    const body = `grant_type=password&username=${encodeURIComponent(usuario)}&password=${encodeURIComponent(contrasena)}`;

    return this.http.post<any>(this.url, body, {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8').set('Authorization', 'Basic ' + btoa(environment.TOKEN_AUTH_USERNAME + ':' + environment.TOKEN_AUTH_PASSWORD))
    });
  }

  /**
   * Si el token no es nulo, entonces el usuario está conectado
   * @returns Un valor booleano.
   */
  estaLogueado() {
    let token = sessionStorage.getItem(environment.TOKEN_NAME);
    return token != null;
  }

  /**
   * Comprueba si hay un token en el almacenamiento de la sesión, si lo hay, envía una solicitud al
   * servidor para invalidar el token, si no, simplemente borra el almacenamiento de la sesión y
   * redirige al usuario a la página de inicio de sesión.
   */
  cerrarSesion() {
    let token = sessionStorage.getItem(environment.TOKEN_NAME);

    if (token) {
      this.http.get(`${environment.HOST}/tokens/anular/${token}`).subscribe(() => {
        sessionStorage.clear();
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('usuario');
        sessionStorage.removeItem(environment.TOKEN_NAME);
        this.router.navigate(['/login']);
      });
    } else {
      sessionStorage.clear();
      sessionStorage.removeItem('token');
      sessionStorage.removeItem('usuario');
      sessionStorage.removeItem(environment.TOKEN_NAME);
      this.router.navigate(['/login']);
    }
  }

 /**
  * Es una solicitud POST que envía una cadena al backend y devuelve un número
  * @param {string} correo - cuerda
  * @returns Un número
  */
  enviarCorreo(correo: string) {
    return this.http.post<number>(`${environment.HOST}/login/enviarCorreo`, correo, {
      headers: new HttpHeaders().set('Content-Type', 'text/plain')
    });
  }

  /**
   * Devuelve un número
   * @param {string} token - cuerda
   * @returns El número de usuarios con el token.
   */
  verificarTokenReset(token: string) {
    return this.http.get<number>(`${environment.HOST}/login/restablecer/verificar/${token}`);
  }

  /**
   * La función toma un token y una contraseña, y envía una solicitud POST al servidor con el token y
   * la contraseña como cuerpo.
   * @param {string} token - El token que se envió al correo electrónico del usuario.
   * @param {string} clave - cuerda
   * @returns La respuesta de la solicitud.
   */
  restablecer(token: string, clave: string) {
    return this.http.post(`${environment.HOST}/login/restablecer/${token}`, clave, {
      headers: new HttpHeaders().set('Content-Type', 'text/plain')
    });
  }

  /**
   * INICIO: Configuración de gestión de accesos de privilegios a usuarios
   */
  /**
   * Si el usuario ha iniciado sesión, devuelva el objeto de usuario. Si el usuario no ha iniciado
   * sesión, pero hay un objeto de usuario en el almacenamiento de la sesión, devuelva el objeto de
   * usuario del almacenamiento de la sesión. Si el usuario no ha iniciado sesión y no hay ningún
   * objeto de usuario en el almacenamiento de la sesión, devolver un nuevo objeto de usuario
   * @returns El objeto de usuario.
   */
  public get usuario(): TblUsuario {
    if (this._usuario != null) {
      return this._usuario;
    } else if (this._usuario == null && sessionStorage.getItem('usuario') != null) {
      this._usuario = JSON.parse(sessionStorage.getItem('usuario')) as TblUsuario;
      return this._usuario;
    }
    return new TblUsuario();
  }

  /**
   * Si el token no es nulo, devuelva el token; de lo contrario, si el token es nulo y el token de
   * sessionStorage no es nulo, establezca el token en el token de sessionStorage y devuelva el token;
   * de lo contrario, devuelva nulo
   * @returns El token está siendo devuelto.
   */
  public get token(): string {
    if (this._token != null) {
      return this._token;
    } else if (this._token == null && sessionStorage.getItem('token') != null) {
      this._token = sessionStorage.getItem('token');
      return this._token;
    }
    return null;
  }

  /**
   * Toma un token JWT y devuelve la carga útil del token
   * @param {string} accessToken - El token de acceso que desea decodificar.
   * @returns La carga útil del token.
   */
  obtenerDatosToken(accessToken: string): any {
    if (accessToken != null) {
      return JSON.parse(atob(accessToken.split(".")[1]));
    }
    return null;
  }

  /**
   * Si la carga del token contiene una propiedad de nombre de usuario, el usuario se autentica
   * @returns Un valor booleano.
   */
  isAuthenticated(): boolean {
    let payload = this.obtenerDatosToken(this.token);
    if (payload != null && payload.user_name && payload.user_name.length > 0 && !this.isTokenExpirado()) {
      return true;
    }
    return false;
  }

 /**
  * Si el token no es nulo y la fecha de vencimiento es anterior a la fecha actual, entonces el token
  * está vencido
  * @returns El token está siendo devuelto.
  */
  isTokenExpirado(): boolean {
    let token = this.token;
    let payload = this.obtenerDatosToken(token);
    let now = new Date().getTime() / 1000;
    if (payload !== null && payload.exp < now) {
      return true;
    }
    return false;
  }

  /**
   * Toma el token de acceso, extrae el nombre de usuario y los roles y los almacena en el
   * almacenamiento de la sesión.
   * @param {string} accessToken - El token de acceso que se devolvió desde el servidor.
   */
  guardarUsuario(accessToken: string): void {
    let payload = this.obtenerDatosToken(accessToken);
    this._usuario = new TblUsuario();
    this._usuario.username = payload.user_name;
    this._usuario.roles = payload.authorities;
    sessionStorage.setItem('usuario', JSON.stringify(this._usuario));
  }

  /**
   * Guarda el token de acceso en el almacenamiento de la sesión.
   * @param {string} accessToken - El token de acceso que obtenemos del servidor.
   */
  guardarToken(accessToken: string): void {
    this._token = accessToken;
    sessionStorage.setItem('token', accessToken);
  }

  /**
   * Me permite dar acceso a las funciones de acciones que van a tener los usuarios de acuerdo al rol asignado
   * @param role
   * @returns
   */
  hasRole(role: string): boolean {
    if (this.usuario.roles.includes(role)) {
      // console.log(role);

      return true;
    }
    return false;
  }

  /**-----------------FIN------------------- */
}
