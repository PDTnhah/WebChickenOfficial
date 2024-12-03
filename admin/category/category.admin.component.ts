import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from '../../../models/category';
import { CategoryService } from '../../../services/category.service';
import { ToastrService } from 'ngx-toastr'; // Thêm ToastrService vào import

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-category-admin',
  templateUrl: './category.admin.component.html',
  styleUrls: [
    './category.admin.component.scss',        
  ],
  standalone: true,
  imports: [   
    CommonModule,
    FormsModule,
  ]
})
export class CategoryAdminComponent implements OnInit {
  categories: Category[] = []; // Dữ liệu động từ categoryService

  constructor(
    private categoryService: CategoryService,
    private router: Router,
    private toastr: ToastrService  // Thêm ToastrService vào constructor
  ) {}

  ngOnInit() {      
    this.getCategories(0, 100);
  }

  getCategories(page: number, limit: number) {
    this.categoryService.getCategories(page, limit).subscribe({
      next: (response: any) => {
        this.categories = response.categories;
      },
      error: (error: any) => {
        console.error('Error:', error);
        this.toastr.error(`Đã xảy ra lỗi: ${JSON.stringify(error.error)}`, 'Lỗi');
      }
    });          
  }

  insertCategory() {
    this.router.navigate(['/admin/categories/insert']);
  } 

  updateCategory(categoryId: number) {
    this.router.navigate(['/admin/categories/update', categoryId]);
  }  

  deleteCategory(category: Category) {
    if (window.confirm('Bạn có chắc chắn muốn xóa danh mục này?')) {
      this.categoryService.deleteCategory(category.id).subscribe({
        next: (response: string) => {
          this.toastr.success('Xóa thành công', 'Thành công');
          this.getCategories(0, 100); // Cập nhật danh sách danh mục
        },
        error: (error: any) => {
          console.error('Error fetching categories:', error);
          this.toastr.success('Xóa thành công', 'Thành công');
          this.getCategories(0, 100);   
        }
      });
    }
  }
}
