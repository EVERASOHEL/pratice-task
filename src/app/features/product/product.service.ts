import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private url = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<any> {
    return this.http.get<any>(`${this.url}/product/getAllProducts`);
  }

  createProduct(product: any): Observable<any[]> {
    return this.http.post<any[]>(`${this.url}/product/createProduct`,product);
  }

}
