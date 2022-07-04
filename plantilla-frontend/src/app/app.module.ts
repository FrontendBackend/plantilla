import { environment } from 'src/environments/environment';
import { LoginTokenComponent } from './login/login-token/login-token.component';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HashLocationStrategy, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { AppRoutes } from './app.routing';
import { AppComponent } from './app.component';

import { FlexLayoutModule } from '@angular/flex-layout';
import { FullComponent } from './layouts/full/full.component';
import { AppHeaderComponent } from './layouts/full/header/header.component';
import { AppSidebarComponent } from './layouts/full/sidebar/sidebar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DemoMaterialModule } from './demo-material-module';

import { SharedModule } from './shared/shared.module';
import { SpinnerComponent } from './shared/spinner.component';
import { LoginIniciarComponent } from './login/login-iniciar/login-iniciar.component';
import { LoginRegistrarComponent } from './login/login-registrar/login-registrar.component';
import { LoginRecuperarComponent } from './login/login-recuperar/login-recuperar.component';
import { CdkTableModule } from '@angular/cdk/table';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginFormularioComponent } from './login/login-formulario/login-formulario.component';
import { JwtModule } from '@auth0/angular-jwt';
import { TokenInterceptor } from './security/interceptors/token.interceptor';
import { ServerErrorsInterceptor } from './security/interceptors/server-errors.interceptor';

export function tokenGetter() {
  return sessionStorage.getItem(environment.TOKEN_NAME);
}
@NgModule({
  declarations: [
    AppComponent,
    FullComponent,
    AppHeaderComponent,
    SpinnerComponent,
    AppSidebarComponent,
    LoginIniciarComponent,
    LoginRegistrarComponent,
    LoginRecuperarComponent,
    LoginFormularioComponent,
    LoginTokenComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    DemoMaterialModule,
    FormsModule,
    FlexLayoutModule,
    HttpClientModule,
    SharedModule,
    RouterModule.forRoot(AppRoutes),
    ReactiveFormsModule,
    CdkTableModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: [environment.HOST.substring(7)],
        disallowedRoutes: [`http://${environment.HOST.substring(7)}/login/enviarCorreo`],
      },
    }),
  ],
  providers: [
    {
      provide: LocationStrategy,
      // useClass: PathLocationStrategy,
      useClass: HashLocationStrategy,
    },
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ServerErrorsInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
