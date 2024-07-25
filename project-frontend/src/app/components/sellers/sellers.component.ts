import { Component, OnInit } from '@angular/core';
import { Seller } from '../../interfaces/seller';
import { SellerService } from '../../services/seller.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-sellers',
  templateUrl: './sellers.component.html',
  styleUrl: './sellers.component.css'
})
export class SellersComponent implements OnInit {

  sellers: Seller[] = [];
  seller: Seller = {} as Seller;

  activateForms: boolean = false;


  constructor(
    private sellerService: SellerService,
    private modalService: NgbModal

  ) { }

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
        console.log(this.seller)
       
        console.log("até aqui tá tudo certo")
      } else {
        this.sellerService.postSeller(this.seller).subscribe({
          next: data => {
            this.sellers.push(data)
            console.log(data)
            
          },
          error: error => console.error(error.error.errors)


        })

      }
      this.reloadPage()
    }
    

    this.seller = {} as Seller;
    
    this.activateForms = false;

    this.loadSellers();

  }

  deleteSeller(modal: any, seller: Seller) {
    this.seller = seller;
    this.modalService.open(modal).result.then(
      (confirm) => {
        if (confirm) {
          this.sellerService.deleteSeller(seller).subscribe({
            next: () => {
              this.sellers = this.sellers.filter(s => s.id !== seller.id)
            }
          })

        }

      }
    )
    this.activateForms = false;
    this.seller = {} as Seller;
    this.loadSellers();



  }
  editSeller(seller: Seller) {
    this.seller = seller;
    this.activateForms = true;
    


  }

  addSeller() {
    this.activateForms = true;
    

  }

  
  reloadPage(){
    location.reload()
   }







}
