import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr'; // Import ToastrService
import { environment } from '../../../../environments/environment';
import { OrderResponse } from '../../../responses/order/order.response';
import { OrderService } from '../../../services/order.service';
import { OrderDTO } from '../../../dtos/order/order.dto';

@Component({
  selector: 'app-detail-order-admin',
  templateUrl: './detail.order.admin.component.html',
  styleUrls: ['./detail.order.admin.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
  ]
})
export class DetailOrderAdminComponent implements OnInit {
  orderId: number = 0;
  orderResponse: OrderResponse = {
    id: 0,
    user_id: 0,
    fullname: '',
    phone_number: '',
    province: '',
    district: '',
    commune: '',
    email: '',
    address: '',
    note: '',
    order_date: new Date,
    status: '',
    total_money: 0,
    shipping_method: '',
    shipping_address: '',
    shipping_date: new Date(),
    payment_method: '',
    order_details: [],
  };

  private orderService = inject(OrderService);
  private toastr = inject(ToastrService); // Inject ToastrService

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getOrderDetails();
  }

  getOrderDetails(): void {
    this.orderId = Number(this.route.snapshot.paramMap.get('id'));
    this.orderService.getOrderById(this.orderId).subscribe({
      next: (response: any) => {
        this.orderResponse = { ...response };
        this.orderResponse.shipping_date = new Date(response.shipping_date);
        this.orderResponse.shipping_date.setDate(this.orderResponse.shipping_date.getDate() + 3);
        this.orderResponse.order_details = response.order_details.map((order_detail: any) => {
          order_detail.product.thumbnail = `${environment.apiBaseUrl}/products/images/${order_detail.product.thumbnail}`;
          order_detail.number_of_products = order_detail.numberOfProducts;
          return order_detail;
        });
      },
      error: (error: any) => {
        this.toastr.error('Error fetching order details', 'Error');
      },
    });
  }

  saveOrder(): void {
    this.orderService.updateOrder(this.orderId, new OrderDTO(this.orderResponse)).subscribe({
      next: (response: any) => {
        debugger
        this.orderResponse.order_date = response.order_date;
        this.orderResponse.shipping_date = new Date(this.orderResponse.shipping_date);
          // order.shipping_date.setDate(order.shipping_date.getDate() + 3);
        this.orderResponse.shipping_date.setDate(this.orderResponse.shipping_date.getDate() + 3 );
        this.toastr.success(`Cập nhật đơn hàng của ${response.fullname}`, 'Thành công');
        this.router.navigate(['../'], { relativeTo: this.route });
      },
      error: (error: any) => {
        this.toastr.error('Lỗi cập nhật đơn hàng', 'Error');
        this.router.navigate(['../'], { relativeTo: this.route });
      }
    });
  }
}
