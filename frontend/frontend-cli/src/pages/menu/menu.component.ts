import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-menu',
  standalone: true,
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  imports: [CommonModule, RouterModule]
})
export class MenuComponent {
  get role(): string {
    return localStorage.getItem('role') || '';
  }

  get isLoggedIn(): boolean {
    return !!localStorage.getItem('username');
  }

  logout() {
    localStorage.clear();
    location.href = '/login';
  }
}