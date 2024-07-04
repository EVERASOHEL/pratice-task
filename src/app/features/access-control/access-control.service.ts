import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';

interface UserPermission {
  userPermissionDTOList: UserPermissionDTO[];
  moduleDTOList: ModuleDTO[];
}

interface UserPermissionDTO {
  id: number | null;
  userId: number;
  userName: string;
  selectedModules: any[] | null;
  roleName: string;
}

interface ModuleDTO {
  id: number | null;
  moduleName: string;
  description: string | null;
  permissionDTO: PermissionDTO[];
}

interface PermissionDTO {
  id: number | null;
  permissionName: string;
  selected: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class AccessControlService {

  private url = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getAllUserPermissionModule(): Observable<UserPermission[]> {
    return this.http.get<{ message: string; statusCode: number; data: UserPermission[] }>(`${this.url}/rolePermission/getAllAccessControl`)
      .pipe(
        map(response => response.data)
      );
  }

  getAllUsers():Observable<any[]>{
    return this.http.get<any>(`${this.url}/auth/getAllUsers`)
  }

  getAllModuleNameList(): Observable<any[]> {
    return this.http.get<any[]>(`${this.url}/rolePermission/getAllPermissionList`);
  }

  saveNewPermission(payload:any):Observable<any[]>{
    return this.http.post<any[]>(`${this.url}/rolePermission/createNewPermission`,payload);
  }
}
