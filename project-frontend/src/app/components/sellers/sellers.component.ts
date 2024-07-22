import { Component, OnInit } from '@angular/core';
import { Seller } from '../../interfaces/seller';
import { SellerService } from '../../services/seller.service';

@Component({
  selector: 'app-sellers',
  templateUrl: './sellers.component.html',
  styleUrl: './sellers.component.css'
})
export class SellersComponent implements OnInit {

  sellers: Seller[] = [];
  seller: Seller = {} as Seller;


  constructor(private sellerService: SellerService) { }

  ngOnInit(): void {
    this.loadSellers();

  }

  loadSellers() {
    this.sellerService.getSellers().subscribe({
      next: data => this.sellers = data
    });
  }

  saveSeller(save: Boolean) {
    if (save) {
      if (this.seller.id) {
        this.sellerService.putSeller(this.seller).subscribe();
      } else {
        this.sellerService.postSeller(this.seller).subscribe({
          next: data => {
            this.sellers.push(data)
            console.log(data)
          },
          error: error => console.error(error.error.errors)


        })

      }
    }

    this.seller = {} as Seller;
    this.loadSellers();
  }

  deleteSeller(seller:Seller) {
    
    
  }
  editSeller(seller:Seller) {
    
  }




}
