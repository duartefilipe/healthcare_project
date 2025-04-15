import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  standalone: true,
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [CommonModule, FormsModule]
})
export class LoginComponent {
  username = '';
  password = '';
  error = '';

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    this.http.post<any>('http://localhost:8081/auth/login', {
      username: this.username,
      password: this.password
    }).subscribe({
      next: res => {
        if (res && res.username && res.role) {
          localStorage.setItem('username', res.username);
          localStorage.setItem('password', this.password);
          localStorage.setItem('role', res.role);
          localStorage.setItem('name', res.username); 
          localStorage.setItem('userId', res.id);
          alert(`Bem-vindo, ${res.username}!`);
          this.router.navigate(['/home']);
        } else {
          this.error = 'Erro inesperado no login';
        }
      },
      error: () => {
        this.error = 'Usuário ou senha inválidos';
      }
    });
  }
  
}
