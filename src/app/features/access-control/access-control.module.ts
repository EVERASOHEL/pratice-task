import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccessControlRoutingModule } from './access-control-routing.module';
import { AccessRequestComponent } from './access-request/access-request.component';
import { AccessListComponent } from './access-list/access-list.component';


@NgModule({
  declarations: [
    AccessRequestComponent,
    AccessListComponent
  ],
  imports: [
    CommonModule,
    AccessControlRoutingModule
  ]
})
export class AccessControlModule { }
