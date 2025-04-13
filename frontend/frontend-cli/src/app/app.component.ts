import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [CommonModule, RouterModule]
})
export class AppComponent {
  title = 'frontend';

  get isLoggedIn(): boolean {
    return !!localStorage.getItem('username');
  }

  get isAdmin(): boolean {
    return localStorage.getItem('role') === 'ADMIN';
  }

  logout() {
    localStorage.clear();
    location.href = '/login';
  }
}
