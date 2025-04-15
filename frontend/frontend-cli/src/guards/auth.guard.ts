import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = () => {
  const router = inject(Router);
  const name = localStorage.getItem('name');

  if (!name) {
    router.navigate(['/login']);
    return false;
  }

  return true;
};
