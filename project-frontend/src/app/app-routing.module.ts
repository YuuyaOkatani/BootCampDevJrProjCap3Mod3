import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SellersComponent } from './components/sellers/sellers.component';

const routes: Routes = [
  {path: '#', component:HomeComponent},
  {path: "sellers", component: SellersComponent},
  {path:'',redirectTo:'home', pathMatch:'full'}, 
  {path: '**', redirectTo:'home', pathMatch:'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
