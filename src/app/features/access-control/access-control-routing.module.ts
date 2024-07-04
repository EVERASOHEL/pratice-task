import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccessRequestComponent } from './access-request/access-request.component';
import { AccessListComponent } from './access-list/access-list.component';

const routes: Routes = [
  {path: '', redirectTo:'list',pathMatch:'full'},
  { path: 'request', component: AccessRequestComponent },
  { path: 'list', component: AccessListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccessControlRoutingModule { }
