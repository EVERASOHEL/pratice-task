import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductRequestComponent } from './product-request/product-request.component';

const routes: Routes = [
  { path: '', redirectTo: 'list', pathMatch: 'full' },
  { path: 'list', component: ProductListComponent },
  { path: 'request', component: ProductListComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    // Import standalone components
    ProductListComponent,
    ProductRequestComponent
  ],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
