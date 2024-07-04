import { Component, OnInit } from '@angular/core';
import { AccessControlService } from '../access-control.service';
import { ToastrService } from 'ngx-toastr';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTableModule } from '@angular/material/table';
import { CommonModule, NgFor } from '@angular/common';
import { Router } from '@angular/router';
import { PermissionService } from '../../../permission.service';

interface UserPermission {
  userPermissionDTOList: UserPermissionDTO[];
  moduleDTOList: ModuleDTO[];
}

interface UserPermissionDTO {
  id: number | null;
  userId: number;
  userName: string;
  selectedModules: any[] | null;
  roleName: string;
}

interface ModuleDTO {
  id: number | null;
  moduleName: string;
  description: string | null;
  permissionDTO: PermissionDTO[];
}

interface PermissionDTO {
  id: number | null;
  permissionName: string;
  selected: boolean;
}

@Component({
  selector: 'app-access-list',
  templateUrl: './access-list.component.html',
  styleUrls: ['./access-list.component.scss'],
  imports: [
    MatIconModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatExpansionModule,
    MatTableModule,
    NgFor,
    CommonModule
  ],
  standalone: true
})
export class AccessListComponent implements OnInit {

  userPermissions: UserPermission[] = [];
  panelOpenState: boolean[] = [];
  errorMessage: string | null = null;

  constructor(
    private accessControlService: AccessControlService,
    private toastr: ToastrService,
    private router:Router,
    public permissionService: PermissionService
  ) { }

  navigateToRequest(): void {
    this.router.navigate(['/permission/request']);
  }

  ngOnInit(): void {
    this.accessControlService.getAllUserPermissionModule()
      .subscribe(response => {
        this.userPermissions = response;
        this.panelOpenState = new Array(this.userPermissions.length).fill(false);
      },
      (error) => {
        this.errorMessage = error.error.message;
        this.toastr.error("this.errorMessage", 'Failed to load permissions');
      });
  }

  togglePanel(index: number): void {
    this.panelOpenState[index] = !this.panelOpenState[index];
  }

  isPermissionSelected(permission: UserPermission, moduleName: string, permissionName: string): boolean {
    const module = permission.moduleDTOList.find(mod => mod.moduleName === moduleName);
    return module ? module.permissionDTO.some(p => p.permissionName === permissionName && p.selected) : false;
  }
}
