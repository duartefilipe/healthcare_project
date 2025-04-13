import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  standalone: true,
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  imports: [CommonModule, FormsModule]
})
export class PatientFormComponent {
  patient = {
    name: '',
    document: '',
    birthdate: ''
  };

  constructor(private http: HttpClient) {}

  submit() {
    const username = 'user';
    const password = 'password';
    const basicAuth = 'Basic ' + btoa(`${username}:${password}`);

    const headers = new HttpHeaders({
      'Authorization': basicAuth,
      'Content-Type': 'application/json'
    });

    this.http.post('http://localhost:8080/patients', this.patient, {
      withCredentials: true
    })
    .subscribe(() => {
      alert('Paciente salvo com sucesso!');
      this.patient = { name: '', document: '', birthdate: '' };
    });
    
  }
}
