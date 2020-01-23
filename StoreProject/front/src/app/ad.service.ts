import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ad } from './model/Ad';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdService {


  private readonly path = "api/ads";

  private pageSize = 10;
  constructor(private http: HttpClient) { }


  getAll(page: number, title: string): Observable<Ad[]> {
    return this.http.get<Ad[]>(this.path + `?page=${page}&size=${this.pageSize}&title=${title}`);
  }
  getAd(id: number): Observable<Ad>{
    return this.http.get<Ad>(`${this.path}/${id}`);
  }
  add(ad: Ad): Observable<Ad>{
    return this.http.post<Ad>(this.path, ad);
  }
  edit(ad: Ad): Observable<Ad>{
    return this.http.put<Ad>(`${this.path}/${ad.id}`, ad);
  }
  delete(id: number): Observable<Ad>{
    return this.http.delete<Ad>(`${this.path}/${id}`);
  }


}
