import { Component, Input, Output,EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { Seller } from '../../interfaces/seller';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-seller',
  templateUrl: './seller.component.html',
  styleUrl: './seller.component.css'
})
export class SellerComponent implements OnChanges {

  @Input()
  seller: Seller = {} as Seller

  @Output()
  saveEmitter = new EventEmitter();


  forms: FormGroup; 

  constructor(private formbuilder: FormBuilder) {
    this.forms = this.formbuilder.group({
      id: [''],
      name: ['', [Validators.required, Validators.minLength(5)]],
      salary: ['', [Validators.required,  Validators.min(1)]],
      bonus: [0, [Validators.required,  Validators.min(0), Validators.max(100)]],
      gender: ['',[Validators.required]],
    });
   }
   ngOnChanges(): void {
    if(this.seller.id){
      this.forms.setValue(this.seller)
    }
       
   }

   get validName(){
    return this.forms.get('name')
   }

   get validSalary(){
    return this.forms.get('salary')
   }
   get validBonus(){
    return this.forms.get('bonus')
   }

   get validGender(){
    return this.forms.get('gender')
   }

   save(){
    if(this.forms.valid){
      Object.assign(this.seller, this.forms.value) // ==> coloca os valores do formulário para o objeto seller
      this.saveEmitter.emit(true)

    }
   }

   cancel(){
    this.saveEmitter.emit(false)
   }



   


   


}
