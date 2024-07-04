import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './features/login/login.component';
import { HomeComponent } from './features/home/home.component';
import { ProductListComponent } from './features/product/product-list/product-list.component';
import { LoginGuard } from './guards/login.guard';
import { AuthGuard } from './guards/auth.guard';
import { RoleGruard } from './guards/role.guard';
import { CompanyListComponent } from './features/company/company-list/company-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard] },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  {
    path: 'product',
    loadChildren: () => import('./features/product/product.module').then(m => m.ProductModule),
    canActivate: [AuthGuard, RoleGruard],
    data: { requiredPermission: 'canRead', requiredRole: '3' }
  },
  {
    path: 'permission',
    loadChildren: () => import('./features/access-control/access-control-routing.module').then(p => p.AccessControlRoutingModule),
    canActivate: [AuthGuard, RoleGruard],
    data: { requiredPermission: 'canRead', requiredRole: '3' }
  },
  {
    path: 'company',
    loadChildren: () => import('./features/company/company.module').then(m => m.CompanyModule),
    canActivate: [AuthGuard, RoleGruard],
    data: { requiredPermission: 'canRead', requiredRole: '3' }
  },
  // { path: 'company/list', component: CompanyListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
