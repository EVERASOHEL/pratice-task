import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CompanyService } from '../company.service';
import { ToastrService } from 'ngx-toastr';
import { response } from 'express';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatExpansionModule } from '@angular/material/expansion';
import { PermissionService } from '../../../permission.service';
import { MatDialog } from '@angular/material/dialog';
import { CompanyRequestComponent } from '../company-request/company-request.component';

interface userData{
  userId:string,
  email:string,
  userName:string,
  rolename:string
}

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrl: './company-list.component.scss',
  standalone: true,
  imports:[
    MatIconModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatExpansionModule,
    MatTableModule,
    NgFor,
    CommonModule
  ]
})
export class CompanyListComponent implements OnInit {

  displayedColumns: string[] = ['position', 'id', 'email', 'userName', 'name'];
  dataSources = new MatTableDataSource<any>([]);

  users:userData[]=[];

  constructor(
    private toastr: ToastrService,
    private route: ActivatedRoute,
    private companyService: CompanyService,
    private router:Router,
    public dialog: MatDialog,
    public permissionService: PermissionService
  ) { }

  navigateToRequest(): void {
    this.router.navigate(['/company/request']);
  }

  ngOnInit() {
    console.log("call dropdown");
    this.route.url.subscribe(url => {
      if (url.length && url[0].path === 'request') {
        this.openDialog();
      }
    });
    this.getAllUser();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(CompanyRequestComponent, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe(result => {
      this.router.navigate(['/company']);
    });
  }

  getAllUser(): void {
    const authToken = localStorage.getItem('authToken');
    if (authToken) {
      this.companyService.getAllUser().subscribe(
        (response:any) => {
          if (response && response.data) {
            const transformedData = response.data.map((user: any, index: number) => ({
              position: index + 1,
              id: user.id,
              userName: user.userName,
              email: user.email,
              name: user.roleDTO.map((role: any) => role.name).join(', ')
            }));
            this.dataSources.data = transformedData;
            console.log('Response of users:', response);
          } else {
            console.log('No data found in response.');
            this.toastr.warning('No users found', 'Load Users');
          }
        },
        (error) => {
          console.log("error : ", error);
          this.toastr.error(error.error.message, 'Failed to load users');
        }
      )
    }else{
      console.error('No auth token found!');
      this.router.navigate(['/login']);
    }
  }
}
