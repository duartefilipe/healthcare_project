import { Routes } from '@angular/router';
import { HomeComponent } from '../pages/home/home.component';
import { LoginComponent } from '../pages/login/login.component';
import { UsersComponent } from '../pages/users/users.component';
import { authGuard } from '../guards/auth.guard';
import { adminGuard } from '../guards/admin.guard';
import { AppointmentsComponent } from '../pages/appointments/appointments.component';
import { PatientsComponent } from '../pages/patients/patients.component';
import { adminOrMedicoGuard } from '../guards/admin-or-medico.guard'; 

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent, canActivate: [authGuard] },

  { path: 'patients', component: PatientsComponent, canActivate: [authGuard, adminOrMedicoGuard] },
  { path: 'appointments', component: AppointmentsComponent, canActivate: [authGuard] },

  { path: 'users', component: UsersComponent, canActivate: [authGuard, adminGuard] }
];
