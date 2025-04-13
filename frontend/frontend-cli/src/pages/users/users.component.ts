import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-users',
  standalone: true,
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
  imports: [CommonModule, FormsModule]
})
export class UsersComponent implements OnInit {
  users: any[] = [];
  user = {
    id: null,
    username: '',
    password: '',
    role: ''
  };

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchUsers();
  }

  /** Monta os headers com Authorization Basic */
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

  fetchUsers() {
    this.http.get<any[]>('http://localhost:8081/users', this.getAuthHeaders())
      .subscribe(data => this.users = data);
  }

  submit() {
    const method = this.user.id ? 'put' : 'post';
    const url = this.user.id
      ? `http://localhost:8081/users/${this.user.id}`
      : 'http://localhost:8081/users';

    const payload = method === 'post'
      ? {
          username: this.user.username,
          password: this.user.password,
          role: this.user.role
        }
      : { ...this.user };

    this.http[method](url, payload, this.getAuthHeaders())
      .subscribe(() => {
        this.fetchUsers();
        this.resetForm();
      });
  }

  edit(user: any) {
    this.user = { ...user };
  }

  delete(id: number) {
    this.http.delete(`http://localhost:8081/users/${id}`, this.getAuthHeaders())
      .subscribe(() => this.fetchUsers());
  }

  resetForm() {
    this.user = { id: null, username: '', password: '', role: '' };
  }
}
