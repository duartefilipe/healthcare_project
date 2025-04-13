import { Routes } from '@angular/router';
import { HomeComponent } from '../pages/home/home.component';
import { PatientFormComponent } from '../pages/patient-form/patient-form.component';
import { PatientListComponent } from '../pages/patient-list/patient-list.component';
import { LoginComponent } from '../pages/login/login.component';
import { UsersComponent } from '../pages/users/users.component';
import { authGuard } from '../guards/auth.guard';
import { adminGuard } from '../guards/admin.guard';


export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent, canActivate: [authGuard] },
  { path: 'patients/form', component: PatientFormComponent, canActivate: [authGuard] },
  { path: 'patients', component: PatientListComponent, canActivate: [authGuard] },
  { path: 'users', component: UsersComponent, canActivate: [authGuard, adminGuard] }
];
