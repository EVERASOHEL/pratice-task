import { Component, OnInit } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { ProductService } from '../product.service';
import { ToastrService } from 'ngx-toastr';
import { ProductRequestComponent } from '../product-request/product-request.component';
import { CommonModule } from '@angular/common';
import { PermissionService } from '../../../permission.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss'],
  standalone: true,
  imports: [MatTableModule, MatPaginatorModule, CommonModule]
})
export class ProductListComponent implements OnInit {

  displayedColumns: string[] = ['position', 'productName', 'price', 'productHSNCode'];
  dataSource = new MatTableDataSource<any>([]);

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    public dialog: MatDialog,
    private productService: ProductService,
    private toastr: ToastrService,
    public permissionService: PermissionService
  ) {

    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.getAllProducts();
      }
    }

    )

  }

  ngOnInit() {
    this.route.url.subscribe(url => {
      if (url.length && url[0].path === 'request') {
        this.openDialog();
      }
    });
    this.getAllProducts();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ProductRequestComponent, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe(result => {
      this.router.navigate(['/product']);
    });
  }

  navigateToRequest(): void {
    this.router.navigate(['/product/request']);
  }

  getAllProducts(): void {
    const authToken = localStorage.getItem('authToken');
    if (authToken) {
      this.productService.getAllProducts().subscribe(
        (response) => {
          this.dataSource.data = response.data;
        },
        (error) => {
          this.toastr.error(error.error.message, 'Failed to load products');
        } 
      );
    } else {
      console.error('No auth token found!');
      this.router.navigate(['/login']);
    }
  }
}
