import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MainPageComponent } from './main-page/main-page.component';
import { AdPageComponent } from './ad-page/ad-page.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'main', component: MainPageComponent},
  { path: 'main/:id', component: AdPageComponent},
  { path: '', redirectTo: '#', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
