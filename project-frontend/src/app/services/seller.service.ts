import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Seller } from '../interfaces/Seller';

@Injectable({
  providedIn: 'root'
})
export class SellerService {

  constructor(private http: HttpClient) { }

  url = 'http://localhost:8080/sellers'

  getSellers(): Observable<Seller[]>{
    return this.http.get<Seller[]>(this.url);
  }

  getSeller(seller: Seller): Observable <Seller>{
    return this.http.get<Seller>(`${this.url}/${seller.id}`);
  }

  postSeller(seller: Seller): Observable<Seller>{
    return this.http.post<Seller>(this.url, seller);
  }

  putSeller(seller: Seller): Observable<Seller>{
    return this.http.put<Seller>(`${this.url}/${seller.id}`, seller);
  }

  deleteSeller(seller: Seller): Observable<Seller>{
    return this.http.delete<Seller>(`${this.url}/${seller.id}`);
  }

}
