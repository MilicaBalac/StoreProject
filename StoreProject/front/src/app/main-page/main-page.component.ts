import { Component, OnInit } from '@angular/core';
import { AdService } from '../ad.service';
import { Ad } from '../model/Ad';
import { AuthenticationService } from '../security-service/authentication.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  ads: Ad[] = [];

  ad: Ad = {
    id: 0,
    title: "",
    description: "",
    location:"",
    price:0,
    readme:""
  };

  page=0;
  title="";

  isAdministrator: boolean;
  

  constructor(private adService: AdService, private authService: AuthenticationService) { }

  ngOnInit() {
    this.isAdministrator=this.authService.getCurrentUser() ?
    this.authService.getCurrentUser().roles.indexOf('ROLE_ADMIN') !== -1 : false;
    this.isLoggedIn();

    this.loadData();
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  loadData(){
    this.adService.getAll(this.page, this.title).subscribe(data => this.ads = data);
    
  }

  nextPage() {
    this.page += 1;
    this.loadData();
  }

  prevPage() {
    if (this.page >= 0) {
      this.page -= 1;
      this.loadData();
    }
  }
  search() {
    this.loadData();
    this.title="";
  }

  delete(id: number) {
    this.adService.delete(id).subscribe(res => this.loadData());  
    }
  
    markAdForEdit(ad: Ad){
      this.ad = {
        id: ad.id,
        title: ad.title,
        description: ad.description,
        location: ad.location,
        price: ad.price,
        readme: ad.readme
      };
    }
  
    edit(ad: Ad){
      this.adService.edit(ad).subscribe(res=> this.loadData());
    }
  
    add(ad: Ad){
      this.adService.add(ad).subscribe(res=> this.loadData())
    }

    save(){
      if(this.ad.id){
        this.edit(this.ad)
      } else {
        this.add(this.ad)
      }
    }



}
