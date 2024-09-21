import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environments';

@Injectable({
  providedIn: 'root'
})
export class CajeroService {

  constructor( private http: HttpClient) { }
  ruta = environment.API_URL + '/Cajeros'
  getCajeros(): Observable<any> {
    return this.http.get(`${this.ruta}`);
  }

  createCajero(cajero: any): Observable<any> {
    return this.http.post(`${this.ruta}`, cajero);
  }

  updateCajero(cajero: any): Observable<any> {
    return this.http.put(`${this.ruta}`, cajero);
  }

  Bajar(id: number): Observable<any> {
    return this.http.get(`${this.ruta}/Baja/${id}`);
  }

  Restaurar(id: number): Observable<any> {
    return this.http.get(`${this.ruta}/Restore/${id}`);
  }

}
