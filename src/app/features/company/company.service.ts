import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http:HttpClient) { }

  private url=environment.apiUrl;

  getAllUser():Observable<any>{
    return this.http.get<any>(`${this.url}/auth/users`);
  }

  getAllRoles():Observable<any>{
    return this.http.get<any>(`${this.url}/rolePermission/getAllRoles`);
  }

  createNewUser(user:any){
    return this.http.post<any>(`${this.url}/auth/register`,user);
  }
}
