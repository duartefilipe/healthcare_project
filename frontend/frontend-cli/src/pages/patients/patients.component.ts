import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

interface Patient {
  id: number;
  name: string;
  document: string;
  birthDate: string;
  healthCardNumber: string;
  doctorId?: number;
}

interface Doctor {
  id: number;
  name: string;
}

@Component({
  standalone: true,
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  imports: [CommonModule, FormsModule]
})
export class PatientsComponent implements OnInit {
  patients: Patient[] = [];
  form: Partial<Patient> = {};
  editMode = false;
  role = '';
  userId = 0;
  doctors: Doctor[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.role = localStorage.getItem('role')?.toUpperCase() || '';
    this.userId = +(localStorage.getItem('userId') || '0');

    if (this.role === 'MEDICO') {
      this.form.doctorId = this.userId;
    }

    if (this.role === 'ADMIN') {
      this.http.get<Doctor[]>('http://localhost:8081/auth/listMedicals?role=MEDICO', this.getAuthHeaders())
        .subscribe(data => this.doctors = data);
    }

    this.loadPatients();
  }

  getAuthHeaders() {
    const username = localStorage.getItem('username') || '';
    const password = localStorage.getItem('password') || '';
    const basicAuth = btoa(`${username}:${password}`);
    return {
      headers: new HttpHeaders({
        'Authorization': `Basic ${basicAuth}`
      })
    };
  }

  loadPatients() {
    this.http.get<Patient[]>('http://localhost:8080/patients', this.getAuthHeaders())
      .subscribe(data => this.patients = data);
  }

  savePatient() {
    const userPayload = {
      username: this.form.document,
      password: '123',
      role: 'PACIENTE'
    };

    this.http.post<any>('http://localhost:8081/auth/register', userPayload, this.getAuthHeaders()).subscribe(user => {
      const patientPayload = {
        name: this.form.name,
        document: this.form.document,
        birthDate: this.form.birthDate,
        healthCardNumber: this.form.healthCardNumber,
        doctorId: this.form.doctorId
      };

      this.http.post(`http://localhost:8080/patients/${user.id}`, patientPayload, this.getAuthHeaders())
        .subscribe(() => {
          this.loadPatients();
          this.resetForm();
        });
    });
  }

  edit(patient: Patient) {
    this.form = { ...patient };
    this.editMode = true;
  }

  delete(id: number) {
    this.http.delete(`http://localhost:8080/patients/${id}`, this.getAuthHeaders())
      .subscribe(() => this.loadPatients());
  }

  resetForm() {
    this.form = {};
    this.editMode = false;
    if (this.role === 'MEDICO') {
      this.form.doctorId = this.userId;
    }
  }
}
