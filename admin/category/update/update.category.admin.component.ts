import { Component, OnInit } from '@angular/core';
import { Category } from '../../../../models/category';
import { CategoryService } from '../../../../services/category.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-detail.category.admin',
  templateUrl: './update.category.admin.component.html',
  styleUrls: ['./update.category.admin.component.scss'],
  standalone: true,
  imports: [   
    CommonModule,
    FormsModule,
  ]
})

export class UpdateCategoryAdminComponent implements OnInit {
  categoryId: number;
  updatedCategory: Category;

  constructor(
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,  // Inject ToastrService
  ) {
    this.categoryId = 0;    
    this.updatedCategory = {} as Category;  
  }

  ngOnInit(): void {    
    this.route.paramMap.subscribe(params => {
      this.categoryId = Number(params.get('id'));
      this.getCategoryDetails();
    });
  }

  getCategoryDetails(): void {
    this.categoryService.getDetailCategory(this.categoryId).subscribe({
      next: (response: any) => {        
        this.updatedCategory = { ...response };                        
      },
      error: (error: any) => {
        this.toastr.error('Failed to load category details', 'Error');
      }
    });     
  }

  updateCategory() {
    this.categoryService.updateCategory(this.updatedCategory.id, this.updatedCategory).subscribe({
      next: (response) => {
        this.toastr.success(`Cập nhật loại sản phẩm`, 'Thành công');
        this.router.navigate(['/admin/categories']);
      },
      error: (error: any) => {
        this.toastr.error('Failed to update category', 'Error');
      }
    });
  }
}
