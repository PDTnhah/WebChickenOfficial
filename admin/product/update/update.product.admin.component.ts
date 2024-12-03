import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { Product } from '../../../../models/product';
import { Category } from '../../../../models/category';
import { ProductService } from '../../../../services/product.service';
import { CategoryService } from '../../../../services/category.service';
import { environment } from '../../../../../environments/environment';
import { ProductImage } from '../../../../models/product.image';
import { UpdateProductDTO } from '../../../../dtos/product/update.product.dto';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr'; // Import ToastrService

@Component({
  selector: 'app-detail.product.admin',
  templateUrl: './update.product.admin.component.html',
  styleUrls: ['./update.product.admin.component.scss'],
  standalone: true,
  imports: [   
    CommonModule,
    FormsModule,
  ]
})
export class UpdateProductAdminComponent implements OnInit {
  productId: number;
  product: Product;
  updatedProduct: Product;
  categories: Category[] = [];
  currentImageIndex: number = 0;
  images: File[] = [];

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router,
    private categoryService: CategoryService,
    private location: Location,
    private toastr: ToastrService // Inject ToastrService
  ) {
    this.productId = 0;
    this.product = {} as Product;
    this.updatedProduct = {} as Product;  
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.productId = Number(params.get('id'));
      this.getProductDetails();
    });
    this.getCategories(1, 100);
  }

  getCategories(page: number, limit: number) {
    this.categoryService.getCategories(page, limit).subscribe({
      next: (response: any) => {
        this.categories = response.categories;
      },
      error: (error: any) => {
        this.toastr.error(`Đã xảy ra lỗi: ${JSON.stringify(error.error)}`, 'Error');
      }
    });          
  }

  getProductDetails(): void {
    this.productService.getDetailProduct(this.productId).subscribe({
      next: (product: Product) => {
        this.product = product;
        this.updatedProduct = { ...product };     
        this.updatedProduct.product_images.forEach((product_image: ProductImage) => {
          product_image.image_url = `${environment.apiBaseUrl}/products/images/${product_image.image_url}`;
        });
      },
      error: (error: any) => {
        this.toastr.error('Lỗi khi lấy chi tiết sản phẩm', 'Error');
      }
    });     
  }

  updateProduct() {
    const updateProductDTO: UpdateProductDTO = {
      name: this.updatedProduct.name,
      price: this.updatedProduct.price,
      description: this.updatedProduct.description,
      category_id: this.updatedProduct.category_id
    };
    this.productService.updateProduct(this.product.id, updateProductDTO).subscribe({
      next: (response: any) => {
        debugger
        this.toastr.success('Cập nhật sản phẩm thành công', 'Success');
      },
      complete: () => {
        this.router.navigate(['/admin/products']);        
      },
      error: (error: any) => {
        debugger
        this.toastr.error('Lỗi khi cập nhật sản phẩm', 'Error');
      }
    });  
  }

  showImage(index: number): void {
    if (this.product && this.product.product_images && 
        this.product.product_images.length > 0) {
      if (index < 0) {
        index = 0;
      } else if (index >= this.product.product_images.length) {
        index = this.product.product_images.length - 1;
      }        
      this.currentImageIndex = index;
    }
  }

  thumbnailClick(index: number) {
    this.currentImageIndex = index;
  }  

  nextImage(): void {
    this.showImage(this.currentImageIndex + 1);
  }

  previousImage(): void {
    this.showImage(this.currentImageIndex - 1);
  }  

  onFileChange(event: any) {
    const files = event.target.files;
    if (files.length > 5) {
      this.toastr.warning('Chọn tối đa 5 ảnh', 'Warning');
      return;
    }
    this.images = files;
    this.productService.uploadImages(this.productId, this.images).subscribe({
      next: (imageResponse) => {
        this.toastr.success(`Upload thành công ${imageResponse}`, 'Success');
        this.images = [];       
        this.getProductDetails(); 
      },
      error: (error) => {
        this.toastr.error(JSON.stringify(error.error), 'Error');
      }
    });
  }

  deleteImage(productImage: ProductImage) {
    if (confirm('Bạn có chắc chắn muốn xóa ảnh này?')) {
      this.productService.deleteProductImage(productImage.id).subscribe({
        next: (productImage: ProductImage) => {
          this.toastr.success('Xóa ảnh thành công', 'Success');
          this.getProductDetails(); // Reload product details to reflect the changes
        },        
        error: (error) => {
          this.toastr.error(JSON.stringify(error.error), 'Error');
        }
      });
    }
  }
}
