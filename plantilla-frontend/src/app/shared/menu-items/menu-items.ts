import { Injectable } from '@angular/core';

/* Definición de una interfaz. */
export interface MenuDashboard {
  state: string;
  name: string;
  type: string;
  icon: string;
}

/* Definición de una interfaz. */
export interface Menu {
  state: string;
  name: string;
  type: string;
  icon: string;
  rol: string;
}

/* Una constante que se utiliza para crear los elementos del menú. */
const MENUITEMS_DASHBOARD = [
  {
    state: 'dashboard',
    name: 'DASHBOARD',
    type: 'link',
    icon: 'av_timer',
  },
];

/* Una constante que se utiliza para crear los elementos del menú. */
const MENUITEMS = [

  {
    state: 'auditoria',
    type: 'link',
    name: 'AUDITORIA',
    icon: 'security',
    rol: 'ADMIN'
  },
  {
    state: 'usuario',
    type: 'link',
    name: 'USUARIO',
    icon: 'people',
    rol: 'ADMIN'
  },
  // { state: 'button', type: 'link', name: 'Buttons', icon: 'crop_7_5' },
  // { state: 'grid', type: 'link', name: 'Grid List', icon: 'view_comfy' },
  // { state: 'lists', type: 'link', name: 'Lists', icon: 'view_list' },
  // { state: 'menu', type: 'link', name: 'Menu', icon: 'view_headline' },
  // { state: 'tabs', type: 'link', name: 'Tabs', icon: 'tab' },
  // { state: 'stepper', type: 'link', name: 'Stepper', icon: 'web' },
  // {
  //   state: 'expansion',
  //   type: 'link',
  //   name: 'Expansion Panel',
  //   icon: 'vertical_align_center'
  // },
  // { state: 'chips', type: 'link', name: 'Chips', icon: 'vignette' },
  // { state: 'toolbar', type: 'link', name: 'Toolbar', icon: 'voicemail' },
  // {
  //   state: 'progress-snipper',
  //   type: 'link',
  //   name: 'Progress snipper',
  //   icon: 'border_horizontal'
  // },
  // {
  //   state: 'progress',
  //   type: 'link',
  //   name: 'Progress Bar',
  //   icon: 'blur_circular'
  // },
  // {
  //   state: 'dialog',
  //   type: 'link',
  //   name: 'Dialog',
  //   icon: 'assignment_turned_in'
  // },
  // { state: 'tooltip', type: 'link', name: 'Tooltip', icon: 'assistant' },
  // { state: 'snackbar', type: 'link', name: 'Snackbar', icon: 'adb' },
  // { state: 'slider', type: 'link', name: 'Slider', icon: 'developer_mode' },
  // {
  //   state: 'slide-toggle',
  //   type: 'link',
  //   name: 'Slide Toggle',
  //   icon: 'all_inclusive'
  // },

];


@Injectable()
export class MenuItems {

  /**
   * Devuelve una matriz de objetos MenuDashboard.
   * @returns Una matriz de objetos MenuDashboard.
   */
  getMenuitemDashboard(): MenuDashboard[] {
    return MENUITEMS_DASHBOARD;
  }

  /**
   * Devuelve la matriz MENUITEMS.
   * @returns Una matriz de objetos de menú.
   */
  getMenuitem(): Menu[] {
    return MENUITEMS;
  }
}

