import { Routes } from '@angular/router';

import { FullComponent } from './layouts/full/full.component';
import { LoginFormularioComponent } from './login/login-formulario/login-formulario.component';
import { LoginIniciarComponent } from './login/login-iniciar/login-iniciar.component';
import { LoginRecuperarComponent } from './login/login-recuperar/login-recuperar.component';
import { LoginRegistrarComponent } from './login/login-registrar/login-registrar.component';
import { LoginTokenComponent } from './login/login-recuperar/login-token/login-token.component';

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
  }
];
