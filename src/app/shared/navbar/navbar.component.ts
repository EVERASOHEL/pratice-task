// src/app/shared/components/navbar/navbar.component.ts
import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../auth.service';
import { Router } from '@angular/router';
import { PermissionService } from '../../permission.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
  // standalone:true,
  // imports:[FormsModule]
})
export class NavbarComponent implements OnInit {

  constructor(@Inject(PLATFORM_ID) private platformId: Object,private authService: AuthService, private router: Router,public permissionService:PermissionService) { }

  hasPermission(moduleName:string,permissionType:string):boolean{
    return this.permissionService.hasPermission(moduleName,permissionType);
  }

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      const userMenuButton = document.getElementById('user-menu-button');
      const userDropdown = document.getElementById('user-dropdown');

      userMenuButton?.addEventListener('click', () => {
        userDropdown?.classList.toggle('hidden');
      });
    }
  }

  signOut(): void {
    this.authService.signOut(); // Clear authentication token
    this.router.navigateByUrl('/login'); // Redirect to login page
  }

}
