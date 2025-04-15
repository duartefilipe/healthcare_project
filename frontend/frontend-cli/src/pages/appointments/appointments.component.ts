import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

interface Appointment {
  id: number;
  date: string;
  patientId: number;
  patientName: string;
  doctorId: number;
  doctorName: string;
}

interface Patient {
  id: number;
  name: string;
  healthCardNumber: string;
}

interface Doctor {
  id: number;
  name: string; // Certo conforme backend (MedicalResponse)
  role?: string;
}

@Component({
  standalone: true,
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  imports: [CommonModule, FormsModule]
})
export class AppointmentsComponent implements OnInit {
  appointments: Appointment[] = [];
  patients: Patient[] = [];
  doctors: Doctor[] = [];

  form: Partial<Appointment> = {};
  editMode: boolean = false;
  role: string = '';
  userId: number = 0;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.role = localStorage.getItem('role')?.toUpperCase() || '';
    this.userId = +(localStorage.getItem('userId') || '0');

    this.loadAppointments();

    if (this.role !== 'PACIENTE') {
      this.loadPatients();
    } else {
      this.form.patientId = this.userId;
    }

    if (this.role === 'ADMIN') {
      this.loadDoctors();
    }
  }

  loadAppointments() {
    let url = '';

    switch (this.role) {
      case 'ADMIN':
        url = 'http://localhost:8080/appointments/all';
        break;
      case 'MEDICO':
        url = 'http://localhost:8080/appointments/by-doctor';
        break;
      default:
        url = 'http://localhost:8080/appointments/me';
        break;
    }

    this.http.get<Appointment[]>(url).subscribe(data => {
      this.appointments = data;
    });
  }

  loadPatients() {
    this.http.get<Patient[]>('http://localhost:8080/patients')
      .subscribe(data => {
        this.patients = data;
      });
  }

  loadDoctors() {
    this.http.get<Doctor[]>('http://localhost:8081/users?role=MEDICO')
      .subscribe(data => {
        this.doctors = data;
      });
  }

  saveAppointment() {
    const payload: any = {
      patientId: this.form.patientId,
      date: new Date(this.form.date!).toISOString()
    };

    if (this.role === 'ADMIN') {
      payload.doctorId = this.form.doctorId;
    }

    const request$ = this.editMode && this.form.id
      ? this.http.put(`http://localhost:8080/appointments/${this.form.id}`, payload)
      : this.http.post('http://localhost:8080/appointments', payload);

    request$.subscribe(() => {
      this.loadAppointments();
      this.resetForm();
    });
  }

  edit(appointment: Appointment) {
    this.form = {
      id: appointment.id,
      patientId: appointment.patientId,
      date: appointment.date.slice(0, 16),
      doctorId: appointment.doctorId
    };
    this.editMode = true;
  }

  delete(id: number) {
    this.http.delete(`http://localhost:8080/appointments/${id}`)
      .subscribe(() => this.loadAppointments());
  }

  resetForm() {
    this.form = {};
    this.editMode = false;

    if (this.role === 'PACIENTE') {
      this.form.patientId = this.userId;
    }
  }

  logout() {
    localStorage.clear();
    window.location.href = '/login';
  }
}
