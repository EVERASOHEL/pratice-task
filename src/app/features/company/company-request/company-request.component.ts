import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CompanyService } from '../company.service';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-company-request',
  templateUrl: './company-request.component.html',
  styleUrl: './company-request.component.scss',
  standalone: true,
  imports: [
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatOptionModule,
    CommonModule
  ],
})
export class CompanyRequestComponent implements OnInit {

  roledetails: any[] = [];

  constructor(
    private router: Router,
    private companyService: CompanyService,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.getAllRoles();
  }

  companyFormGroup = new FormGroup({
    email: new FormControl(''),
    userName: new FormControl(''),
    password: new FormControl(''),
    selectedOption: new FormControl('')
  })

  getAllRoles(): void {
    const authToken = localStorage.getItem('authToken');
    if (authToken) {
      this.companyService.getAllRoles().subscribe(
        (response) => {
          this.roledetails = response.data;
          console.log('response.data', response.data);
        },
        (error) => {
          console.log('error', error);
          this.toastr.error('error', error);
        }
      )
    } else {
      console.error('No auth token found!');
      this.toastr.error('Authentication', 'Authentication failed!');
      this.router.navigate(['/login']);
    }
  }

  saveCompany(): void {
    const userDetails = this.companyFormGroup.value;
    const updatedUserDetails = {
      email: userDetails.email,
      userName: userDetails.userName,
      password: userDetails.password,
      roleDTO: [
        {
          name: userDetails.selectedOption
        }
      ]
    }
    this.companyService.createNewUser(updatedUserDetails).subscribe(
      (response) => {
        console.log("data successfully created : ", response);
        this.toastr.success('success', response.message);
        this.router.navigate(['/company/list']);
      },
      (error) => {
        this.toastr.error(error.error.message, 'Authentication failed!');
      }
    )
  }

  closeDialog(): void {
    this.router.navigate(['/product/list']);
  }

}
