import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { InsertProductDTO } from '../../../../dtos/product/insert.product.dto';
import { Category } from '../../../../models/category';
import { CategoryService } from '../../../../services/category.service';
import { ProductService } from '../../../../services/product.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr'; // Import ToastrService

@Component({
  selector: 'app-insert.product.admin',
  templateUrl: './insert.product.admin.component.html',
  styleUrls: ['./insert.product.admin.component.scss'],
  standalone: true,
  imports: [   
    CommonModule,
    FormsModule,
  ]
})
export class InsertProductAdminComponent implements OnInit {
  insertProductDTO: InsertProductDTO = {
    name: '',
    price: 0,
    description: '',
    category_id: 1,
    images: []
  };
  categories: Category[] = []; // Dữ liệu động từ categoryService

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private categoryService: CategoryService,
    private productService: ProductService,
    private toastr: ToastrService // Inject ToastrService
  ) {}

  ngOnInit() {
    this.getCategories(1, 100);
  }

  getCategories(page: number, limit: number) {
    this.categoryService.getCategories(page, limit).subscribe({
      next: (response: any) => {
        this.categories = response.categories;
      },
      error: (error: any) => {
        this.toastr.error(`Đã xảy ra lỗi: ${JSON.stringify(error.error)}`, 'Lỗi');
      }
    });          
  }

  onFileChange(event: any) {
    const files = event.target.files;
    if (files.length > 5) {
      this.toastr.warning('Chọn tối đa 5 ảnh', 'Warning');
      return;
    }
    this.insertProductDTO.images = files;
  }

  insertProduct() {
    this.productService.createProduct(this.insertProductDTO).subscribe({
      next: (response) => {
        if (this.insertProductDTO.images.length > 0) {
          const productId = response.id; // Assuming the response contains the newly created product's ID
          this.productService.uploadImages(productId, this.insertProductDTO.images).subscribe({
            next: (imageResponse) => {
              this.toastr.success(`Danh sách ảnh đã được upload: ${imageResponse}`, 'Thành công');
              this.router.navigate(['../'], { relativeTo: this.route });
            },
            error: (error) => {
              this.toastr.error(`Error uploading images: ${error.error}`, 'Lỗi');
            }
          });          
        }
      },
      error: (error) => {
        this.toastr.error(`Error inserting product: ${error.error}`, 'Error');
      }
    });    
  }
}
