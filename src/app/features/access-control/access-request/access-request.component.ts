import { Component, OnInit } from '@angular/core';
import { AccessControlService } from '../access-control.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgClass, NgFor } from '@angular/common';
import { MatCheckboxChange, MatCheckboxModule } from '@angular/material/checkbox';
import { response } from 'express';

interface Module {
  name: string,
  all: boolean,
  write: boolean,
  read: boolean,
  update: boolean,
  delete: boolean
}

interface SelectedUser {
  userId: string,
  userName: string
}

@Component({
  selector: 'app-access-request',
  templateUrl: './access-request.component.html',
  styleUrl: './access-request.component.scss',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatSelectModule,
    MatOptionModule,
    MatFormField,
    ReactiveFormsModule,
    MatFormFieldModule,
    NgFor,
    FormsModule,
    MatCheckboxModule,
    NgClass
  ]
})
export class AccessRequestComponent implements OnInit {

  modules: Module[] = [];
  userdetails: any[] = [];
  usernames: SelectedUser[] = [];
  selectedUsers: SelectedUser[] = [];
  selectedModule: string | null = null;

  constructor(
    private accessControlService: AccessControlService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.getAllUsers();
    this.geAllModuleName();
  }

  getAllUsers(): void {
    const authToken = localStorage.getItem('authToken');
    if (authToken) {
      this.accessControlService.getAllUsers().subscribe(
        (response: any) => {
          if (response && Array.isArray(response.data)) {
            this.userdetails = response.data;
            this.usernames = this.userdetails.map(user => ({ userId: user.id, userName: user.username }));
          } else {
            console.error('Response data is not an array:', response);
          }
          console.log('response', response);
        },
        (error) => {
          console.log('error', error);
        }
      );
    } else {
      console.error('No auth token found!');
      this.toastr.error('Authentication', 'Authentication failed!');
      this.router.navigate(['/login']);
    }
  }

  geAllModuleName(): void {
    const authToken = localStorage.getItem('authToken');
    if (authToken) {
      this.accessControlService.getAllModuleNameList().subscribe(
        (response: any) => {
          console.log('API Response:', response);
          if (response.data && Array.isArray(response.data)) {
            this.modules = response.data.map((moduleName: any) => ({
              name: moduleName,
              all: false,
              write: false,
              read: false,
              update: false,
              delete: false
            }));

            if (this.modules.length > 0) {
              this.selectedModule = this.modules[0].name;
            }

          } else {
            console.error('Unexpected API response format:', response);
            this.toastr.error('Error', 'Unexpected API response format');
          }

        },
        (error) => {
          console.error('Error fetching module names', error);
          this.toastr.error('Error', 'Failed to fetch module names');
          this.router.navigate(['/login']);
        }
      );
    } else {
      console.error('No auth token found!');
      this.toastr.error('Authentication', 'Authentication failed!');
      this.router.navigate(['/login']);
    }
  }

  setSelectedModule(module: any): void {
    this.selectedModule = module.name;
  }

  selectAll(module: Module): void {
    module.write = module.all;
    module.read = module.all;
    module.update = module.all;
    module.delete = module.all;
  }

  navigateListPage(): void {
    this.router.navigate(['/permission/list']);
  }

  savePermissions(): void {
    const moduleDTO = this.modules.map(module => ({
      moduleName: module.name,
      permissionDTO: [
        { permissionName: "Read", selected: module.read ? 1 : 0 },
        { permissionName: "Write", selected: module.write ? 1 : 0 },
        { permissionName: "Update", selected: module.update ? 1 : 0 },
        { permissionName: "Delete", selected: module.delete ? 1 : 0 }
      ]
    }));

    const userPermissionDTO = this.selectedUsers.map(user => ({
      userId: user.userId,
      userName: user.userName,
      selectedModules: this.modules
        .filter(module => module.all || module.write || module.read || module.update || module.delete)
        .map(module => module.name)
    }));

    const payload = {
      userPermissionDTO,
      moduleDTO
    };

    console.log('Sending permission data:', payload);

    const authToken = localStorage.getItem('authToken');
    if (authToken) {
      this.accessControlService.saveNewPermission(payload).subscribe(
        (response:any) => {
          console.log('response', response);
          this.toastr.success('success', response.message);
        },
        (error) => {
          this.toastr.error('error', error.error.message);
        }
      )
    } else {
      console.error('No auth token found!');
      this.toastr.error('Authentication', 'Authentication failed!');
      this.router.navigate(['/login']);
    }

  }


  resetPermissions(): void {
    this.modules.forEach(module => {
      module.all = false;
      module.write = false;
      module.read = false;
      module.update = false;
      module.delete = false;
    });
  
    this.selectedUsers = [];
    this.selectedModule = null;
    if (this.modules.length > 0) {
      this.selectedModule = this.modules[0].name;
    }
  }
  
}
