import { Component, OnInit } from '@angular/core';
import { Params, ActivatedRoute } from '@angular/router';
import { AdService } from '../ad.service';
import { Ad } from '../model/Ad';
import { Auction } from '../model/Auction';
import { AuctionService } from '../auction.service';

@Component({
  selector: 'app-ad-page',
  templateUrl: './ad-page.component.html',
  styleUrls: ['./ad-page.component.css']
})
export class AdPageComponent implements OnInit {

  ad: Ad = {
    id: 0,
    title:"",
    description:"",
    readme:"",
    location: "",
    price: 0
  }
  auctions: Auction[] = [];
  auction: Auction ={ 
    text:"",
    user:"",
    amount: 0
  }

  constructor(private route: ActivatedRoute, private adService: AdService, private auctionService: AuctionService) { }

  ngOnInit() {
    this.getAd();
  }

  getAd() {
    this.route.params.subscribe((params: Params) => {
      const id = +params['id'];
      this.adService.getAd(id).subscribe(data => {
        this.ad = data;
        console.log(data);
        this.loadAdAuction();
      });
    });
  }
  save() {
    this.auction.adDTO = this.ad;
    console.log("this.ad", this.ad);
    if(this.auction.amount < this.ad.price) {
      alert("You cannot bid below the price or last bid value!");
    }
   
    
    this.auctionService.save(this.auction).subscribe(res => console.log('res', res));
    this.getAd();
  }

  loadAdAuction() {
    this.auctionService.findAll(this.ad.id).subscribe(res => {
      this.auctions= res
    });
  }




}
