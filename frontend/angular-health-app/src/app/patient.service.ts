import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Patient } from './models/patient.model';

@Injectable({ providedIn: 'root' })
export class PatientService {
  constructor(private http: HttpClient) {}

  listPatients() {
    return this.http.get<Patient[]>('/api/patients');
  }

  createPatient(patient: Patient) {
    return this.http.post('/api/patients', patient);
  }
}