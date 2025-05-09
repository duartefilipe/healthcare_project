import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const adminGuard: CanActivateFn = () => {
  const router = inject(Router);
  const role = localStorage.getItem('role');

  if (role !== 'ADMIN') {
    alert('Acesso restrito: somente administradores.');
    router.navigate(['/home']);
    return false;
  }

  return true;
};
