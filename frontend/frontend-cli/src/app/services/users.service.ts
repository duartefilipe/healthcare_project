import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface User {
  id?: number;
  username: string;
  password: string;
  role: string;
}

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private apiUrl = 'http://localhost:8081/users';

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const username = sessionStorage.getItem('username') || '';
    const password = sessionStorage.getItem('password') || '';
    const basicAuth = 'Basic ' + btoa(`${username}:${password}`);

    return new HttpHeaders({
      'Authorization': basicAuth,
      'Content-Type': 'application/json'
    });
  }

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl, {
      headers: this.getAuthHeaders()
    });
  }

  create(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user, {
      headers: this.getAuthHeaders()
    });
  }

  update(user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${user.id}`, user, {
      headers: this.getAuthHeaders()
    });
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, {
      headers: this.getAuthHeaders()
    });
  }
}
