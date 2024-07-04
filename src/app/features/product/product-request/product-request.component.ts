import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import { Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ProductService } from '../product.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-product-request',
  templateUrl: './product-request.component.html',
  styleUrl: './product-request.component.scss',
  standalone: true,
  imports: [MatButtonModule, MatDialogModule,MatFormFieldModule, MatInputModule, MatIconModule,ReactiveFormsModule],
})
export class ProductRequestComponent{

  constructor(
    private router:Router,
    private productService:ProductService,
    private toastr: ToastrService
  ){}

  authToken:string | null = '';

  productFormGroup=new FormGroup({
    productName: new FormControl(''),
    price:new FormControl(''),
    productHSNCode:new FormControl('')
  });

  saveProduct():void{
    // if(this.productFormGroup.valid){
      const productData=this.productFormGroup.value;
      this.productService.createProduct(productData).subscribe(
        (response)=>{
          console.log("data successfully created : ",response);
          this.router.navigate(['/product/list']);
        },
        (error)=>{
          this.toastr.error(error.error.message, 'Authentication failed!');
        }
      )
    // }else {
    //   console.error('Form is invalid');
    // }
  }

  closeDialog():void{
    this.router.navigate(['/product/list']);
  }

}