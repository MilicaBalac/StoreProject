import { Injectable } from '@angular/core';
import { AuthenticationService } from './security-service/authentication.service';
import { Ad } from './model/Ad';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Auction } from './model/Auction';

@Injectable({
  providedIn: 'root'
})
export class AuctionService {

  private readonly path = "api/auctions"

  constructor(private http: HttpClient) { }

  save(auction: Auction): Observable<Auction> {
    return this.http.post<Auction>(this.path, auction);
  }

  findAll(id: number): Observable<Auction[]> {
    return this.http.get<Auction[]>(`api/ads/${id}/auctions`);
  }

}
