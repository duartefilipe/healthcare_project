import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CommonModule, DatePipe } from '@angular/common';

@Component({
  selector: 'app-patient-list',
  standalone: true,
  templateUrl: './patient-list.component.html',
  imports: [CommonModule]
})
export class PatientListComponent implements OnInit {
  patients: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa('admin:admin') 
    });

    this.http.get<any[]>('http://localhost:8080/patients', { headers })
      .subscribe(data => {
        this.patients = data;
      });
  }
}
