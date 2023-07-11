import { NbMenuItem } from '@nebular/theme';

export const MENU_ITEMS: NbMenuItem[] = [
  {
    title: 'Dashboard',
    icon: 'nb-home',
    link: '/pages/dashboard',
    home: true,
  },
  {
    title: 'Utenti',
    icon: 'nb-person',
    link: '/pages/users',
    home: false,
  },
  {
    title: 'Eventi',
    icon: 'nb-star',
    link: '/pages/events',
    home: false,
  },
  {
    title: 'Passaggi',
    icon: 'nb-play',
    link: '/pages/rides',
    home: false,
  },
  {
    title: 'Transazioni',
    icon: 'nb-tables',
    link: '/pages/transactions',
    home: false,
  },
  {
    title: 'Catalogo',
    icon: 'nb-tables',
    link: '/pages/catalogs',
    home: false,
  },
  {
    title: 'Admin',
    icon: 'nb-tables',
    link: '/pages/admin',
    home: false,
  },
  // {
  //   title: 'FEATURES',
  //   group: true,
  // },
  // {
  //   title: 'Auth',
  //   icon: 'nb-locked',
  //   children: [
  //     {
  //       title: 'Login',
  //       link: '/auth/login',
  //     },
  //     {
  //       title: 'Register',
  //       link: '/auth/register',
  //     },
  //     {
  //       title: 'Request Password',
  //       link: '/auth/request-password',
  //     },
  //     {
  //       title: 'Reset Password',
  //       link: '/auth/reset-password',
  //     },
  //   ],
  // },
];
