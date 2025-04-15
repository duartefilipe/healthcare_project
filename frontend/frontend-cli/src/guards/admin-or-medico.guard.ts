import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const adminOrMedicoGuard: CanActivateFn = () => {
  const router = inject(Router);
  const role = localStorage.getItem('role');

  if (role === 'ADMIN' || role === 'MEDICO') {
    return true;
  }

  alert('Acesso restrito: apenas administradores ou médicos.');
  router.navigate(['/home']);
  return false;
};
