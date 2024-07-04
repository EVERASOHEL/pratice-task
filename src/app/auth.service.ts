import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from './environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url=environment.apiUrl;

  constructor(private http: HttpClient) { }

  login(userName: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.url}/auth/authenticate`, { userName, password });
  }

  // Method to clear authentication token
  signOut(): void {
    localStorage.removeItem('authToken');
  }
}
