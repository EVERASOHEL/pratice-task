import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth.service';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgToastService } from 'ng-angular-popup';
import { ToastrService } from 'ngx-toastr';
import { NavbarComponent } from '../../shared/navbar/navbar.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {

  constructor(
    private authService: AuthService, 
    private router: Router, 
    private toastr: ToastrService
  ) { }

  loginFormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  loginSuccess = false;
  message = '';

  login() {
    // if (this.loginFormGroup.valid) {
      const { username, password } = this.loginFormGroup.value;
      this.authService.login(username!, password!).subscribe(
        (response) => {
          console.log("Login successfully! : ", response);
          this.loginSuccess = true;
          this.toastr.success(response.message, 'Success');
          localStorage.setItem('authToken', response.token);
          localStorage.setItem('modulePermissions',JSON.stringify(response.modulePermissions.modulePermissions));
          localStorage.setItem('role_Id',response.modulePermissions.role_Id.toString());
          localStorage.setItem('role_Name',response.modulePermissions.role_Name);
          this.router.navigate(['/home']);
          
        },
        (error) => {
          console.error('Login error:', error);
        }
      );
    // } else {
    //   console.error('Form is invalid');
    // }
  }
}
