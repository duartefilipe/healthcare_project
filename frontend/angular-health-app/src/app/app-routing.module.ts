import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { PatientsComponent } from './pages/patients/patients.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'patients', component: PatientsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
