import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyListComponent } from './company-list/company-list.component';
import { CompanyRequestComponent } from './company-request/company-request.component';

const routes: Routes = [
  { path: '', redirectTo: 'list', pathMatch: 'full' },
  { path: 'request', component: CompanyListComponent },
  { path: 'list', component: CompanyListComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    // Import standalone components
    CompanyListComponent,
    CompanyRequestComponent
  ],
  exports: [RouterModule]
})
export class CompanyRoutingModule { }
