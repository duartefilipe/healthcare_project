import { HttpInterceptorFn } from '@angular/common/http';

export const AuthInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('auth');
  const headers = req.headers.set('Authorization', token || '');
  const cloned = req.clone({ headers });
  return next(cloned);
};