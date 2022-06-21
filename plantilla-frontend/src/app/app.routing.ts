import { Routes } from '@angular/router';
import { Not404Component } from './features/error/not404/not404.component';

import { FullComponent } from './layouts/full/full.component';
import { LoginFormularioComponent } from './login/login-formulario/login-formulario.component';
import { LoginIniciarComponent } from './login/login-iniciar/login-iniciar.component';
import { LoginRecuperarComponent } from './login/login-recuperar/login-recuperar.component';
import { LoginTokenComponent } from './login/login-token/login-token.component';
import { GuardServiceService } from './services/guard-service.service';

export const AppRoutes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login'
  },
  {
    path: 'login',
    component: LoginIniciarComponent
  },
  {
    path: '1',
    component: LoginFormularioComponent
  },
  {
    path: 'recuperar',
    component: LoginRecuperarComponent,
    children: [
      { path: ':token', component: LoginTokenComponent }
    ]
  },

  {
    path: '',
    component: FullComponent,
    canActivate: [GuardServiceService],
    children: [
      {
        path: '',
        loadChildren: () => import('./features/features.module').then(m => m.FeaturesModule)
      },
      {
        path: 'dashboard',
        loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)
      }
    ]
  },

  {
    path: 'not-404',
    component: Not404Component,
    data: { title: 'not-404', breadcrumb: 'not-404' }
  },
  {
    path: '**',
    redirectTo: 'not-404'
  }
];
