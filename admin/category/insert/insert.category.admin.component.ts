import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { InsertCategoryDTO } from '../../../../dtos/category/insert.category.dto';
import { Category } from '../../../../models/category';
import { CategoryService } from '../../../../services/category.service';
import { ProductService } from '../../../../services/product.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-insert.category.admin',
  templateUrl: './insert.category.admin.component.html',
  styleUrls: ['./insert.category.admin.component.scss'],
  standalone: true,
  imports: [   
    CommonModule,
    FormsModule,    
  ]
})
export class InsertCategoryAdminComponent implements OnInit {
  insertCategoryDTO: InsertCategoryDTO = {
    name: '',    
  };
  categories: Category[] = []; // Dữ liệu động từ categoryService

  constructor(    
    private route: ActivatedRoute,
    private router: Router,
    private categoryService: CategoryService,    
    private productService: ProductService,    
    private toastr: ToastrService // Inject ToastrService
  ) {}

  ngOnInit() {}

  insertCategory() {    
    this.categoryService.insertCategory(this.insertCategoryDTO).subscribe({
      next: (response) => {
        debugger
        this.toastr.success(`Thêm mới thành công loại sản phẩm ${response.name}`);
        this.router.navigate(['/admin/categories']);        
      },
      error: (error) => {
        debugger
        // Handle error while inserting the category
        this.toastr.error(error.error);
        console.error('Error inserting category:', error);
      }
    });    
  }
}
