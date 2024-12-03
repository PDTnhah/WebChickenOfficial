import { Component, Inject, OnInit, inject } from '@angular/core';
import { CommonModule, DOCUMENT } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { User } from '../../../models/user';
import { NgForm } from '@angular/forms';
import { NgModule } from '@angular/core';
import { Observable } from 'rxjs';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { OrderService } from '../../../services/order.service';
import { ProductService } from '../../../services/product.service';
import { OrderDetailService } from '../../../services/order.detail.service';
import { Chart ,registerables} from 'chart.js';


@Component({
  selector: 'app-sales-revenue',
  templateUrl: './sales-revenue.component.html',
  styleUrl: './sales-revenue.component.scss',
  standalone: true,
  imports: [   
    CommonModule,
    FormsModule,
  ]
})
export class SalesRevenueComponent implements OnInit {
  products: Map<number, string> = new Map();
  orderDetails: Map<number, number> = new Map();
  ordersMoneyDate: Map<string, number> = new Map();
  data_type: string = '';
  constructor(
    private orderService: OrderService,
    private productService: ProductService,
    private orderDetailService: OrderDetailService,
    private toastr : ToastrService,
    private route: ActivatedRoute,
    private router: Router,
    @Inject(DOCUMENT) private document: Document,
  ) {
    Chart.register(...registerables);
  }
  isDropdownOpen = false;
  isCustomDate = false;
  selectedStartDate: Date | null = null;
  selectedEndDate: Date | null = null;
  customStartDate: string | null = null; // Đổi thành string để tương thích với input type="date"
  customEndDate: string | null = null;
  ngOnInit(): void {
    const today = new Date().toISOString().split('T')[0]; // Lấy ngày hôm nay
      this.customStartDate = today; // Thiết lập ngày bắt đầu là hôm nay
      this.customEndDate = today; // Thiết lập ngày kết thúc là hôm nay
      this.selectedEndDate = new Date();
      this.selectedStartDate = new Date();
      this.getAllProducts();
      this.getAllOrderDetails();
      this.getAllOrders();
     
  
  }
  toggleDropdown() {
    debugger
    this.isDropdownOpen = !this.isDropdownOpen;
  }
  
  

  setDateRange(option: string) {
    const today = new Date();
    if (option === 'today') {
      
      this.selectedStartDate = this.selectedEndDate = today;
    } else if (option === 'yesterday') {
      
      const yesterday = new Date(today);
      yesterday.setDate(today.getDate() - 1);
      this.selectedStartDate = this.selectedEndDate = yesterday;
    } else if (option === 'this_week') {
      
      const startOfWeek = new Date(today);
      startOfWeek.setDate(today.getDate() - today.getDay());
      this.selectedStartDate = startOfWeek;
      this.selectedEndDate = today;
    } else if (option === 'last_week') {
      
      const startOfLastWeek = new Date(today);
      startOfLastWeek.setDate(today.getDate() - today.getDay() - 7);
      const endOfLastWeek = new Date(startOfLastWeek);
      endOfLastWeek.setDate(startOfLastWeek.getDate() + 6);
      this.selectedStartDate = startOfLastWeek;
      this.selectedEndDate = endOfLastWeek;
    }
    else if (option === 'this_month') {
      
      const startOfMonth = new Date(today.getFullYear(), today.getMonth(), 1);
      this.selectedStartDate = startOfMonth;
      this.selectedEndDate = today;
    }
    else if (option === 'last_month') {
      
      const startOfMonth = new Date(today.getFullYear(), today.getMonth() - 1, 1); // Ngày bắt đầu của tháng trước
      const endOfMonth = new Date(today.getFullYear(), today.getMonth(), 0); // Ngày kết thúc của tháng trước (ngày cuối cùng của tháng trước)
      this.selectedStartDate = startOfMonth;
      this.selectedEndDate = endOfMonth;
    }
    
    // Thêm logic cho các tùy chọn khác...
    this.isDropdownOpen = false
  
    this.onChartTypeChange("line"); // Vẽ lại biểu đồ sau khi có dữ liệu mới

  }

  setCustomDate() {
    this.isCustomDate = true;
  }

  applyCustomDate() {
    if (this.customStartDate && this.customEndDate) {
      this.selectedStartDate = new Date(this.customStartDate);
      this.selectedEndDate = new Date(this.customEndDate);
      this.isDropdownOpen = false;
      this.isCustomDate = false;
      this.drawChart(); 
    }
  }

  getAllProducts() {
    
    this.productService.getAllProducts().subscribe({
      next: (response) => {
        debugger
        response.products.forEach((product : any) => {
          this.products.set(product.id, product.name);
          this.orderDetails.set(product.id, 0)
        })
        console.log(this.products)
        console.log("oke")
        
      },
      error: (error) => {
        debugger
        console.log(error)
      }
    });
  }
  getAllOrders(): Promise<void> {
    this.ordersMoneyDate.clear();
    return new Promise((resolve, reject) => {
    this.orderService.getAllOrder().subscribe({
      next: (response) => {
        response.forEach((orderMoneyDate: any) => {
          // Chuyển đổi order_date thành chuỗi ngày
          const orderDateString = new Date(orderMoneyDate.order_date).toISOString().split('T')[0];

          // Kiểm tra xem ngày đã tồn tại trong ordersMoneyDate không
          if (!this.ordersMoneyDate.has(orderDateString)) {
            // Thêm ngày vào ordersMoneyDate
            this.ordersMoneyDate.set(orderDateString, orderMoneyDate.total_money);
          } else {
            // Nếu ngày đã tồn tại, cộng tiền đã có vào ordersMoneyDate
            let currentTotalMoney = this.ordersMoneyDate.get(orderDateString) || 0;
            this.ordersMoneyDate.set(orderDateString, currentTotalMoney + orderMoneyDate.total_money);
          }	
        });
        resolve(); // Hoàn tất Promise
      },
      error: (error) => {
        debugger;
        console.log(error);
      }
    });
  })
}

  getAllOrderDetails() { 
    this.orderDetailService.getAllOrderDetails().subscribe({
      next: (response) => {
        debugger;
        response.forEach((orderDetail: any) => {
          // Kiểm tra xem sản phẩm có tồn tại trong orderDetails không
          if (this.orderDetails.has(orderDetail.product_id)) {
            // Lấy số lượng hiện tại và tăng lên 1
            let currentCount = this.orderDetails.get(orderDetail.product_id) || 0;
            this.orderDetails.set(orderDetail.product_id, currentCount + 1);
          }
        });
      },
      error: (error) => {
        console.log(error);
      }
    });
  }
  onChartTypeChange(event: string) {
    // Hàm này được gọi mỗi khi loại biểu đồ thay đổi
    debugger
    this.data_type = event;
    this.drawChart(); // Gọi hàm vẽ biểu đồ tương ứng
  }

  drawChart() {
    setTimeout(() => { // Đảm bảo canvas đã tồn tại trong DOM
      if (this.data_type === 'line') {
        this.drawLineChart();
      } else if (this.data_type === 'bar') {
        this.drawBarChart();
      }
    });
  }
  drawBarChart() {
    const xValues = Array.from(this.products.values());
    const yValues = Array.from(this.orderDetails.values());
    debugger
    new Chart("barChart", {
      type: "bar",
      data: {
        labels: xValues,
        datasets: [{
          // fill: false,
          // tension: 0,
          backgroundColor: "rgba(0,0,255,1.0)",
          borderColor: "rgba(0,0,255,0.1)",
          data: yValues
        }]
      },
      options: {
        plugins: {
          title: { // Thêm tiêu đề vào đây
            display: true,
            text: 'Sản phẩm đã bán',
            position: 'top',
            font: { // Cấu hình font cho tiêu đề
              size: 24, // Kích thước font
              family: 'Arial', // (Tùy chọn) Kiểu chữ
              weight: 'normal', // (Tùy chọn) Độ đậm của chữ
              lineHeight: 1.2 // (Tùy chọn) Chiều cao dòng
            },
            color: '#333' // Màu chữ
          },
          legend: {
            display: false // Đưa legend vào trong plugins
          }
        },
        scales: {
          x: {
            title: {
              display: true,
              text: 'Tên sản phẩm', // Nhãn cho trục X
              font: {
                size: 14,
                family: 'Arial',
              },
              color: '#333' // Màu chữ cho nhãn trục X
            }
          },
          y: {
            title: {
              display: true,
              text: 'Số sản phẩm đã được đặt hàng', // Nhãn cho trục Y
              font: {
                size: 14,
                family: 'Arial'
              },
              color: '#333' // Màu chữ cho nhãn trục Y
            },
            min: 0,
            max: Math.max(...yValues) + 10 // tăng thêm 10 để có khoảng trống trên biểu đ��
          }
        }
      }
    });
  }
  generateDateRange(startDate: Date, endDate: Date): string[] {
    const dateArray = [];
    let currentDate = new Date(startDate);
  
    while (currentDate <= endDate) {
      dateArray.push(currentDate.toISOString().split('T')[0]); // Định dạng YYYY-MM-DD
      currentDate.setDate(currentDate.getDate() + 1); // Thêm 1 ngày
    }
  
    return dateArray;
  }
  
  private chartInstance: any; 
  drawLineChart() {
  const startDate = this.selectedStartDate || new Date(Array.from(this.ordersMoneyDate.keys())[0]);
  const endDate = this.selectedEndDate || new Date(Array.from(this.ordersMoneyDate.keys()).pop()!);
  
  const xValues = this.generateDateRange(startDate, endDate);
  xValues.forEach((date) => {
    const dateString = date; // Chuyển đổi sang chuỗi ngày
    if (!this.ordersMoneyDate.has(dateString)) { // Kiểm tra với chuỗi ngày
      this.ordersMoneyDate.set(dateString, 0); // Thêm với giá trị 0 nếu chưa có
    }
  });
  const yValues = xValues.map(date => this.ordersMoneyDate.get(date) || 0);
    debugger
    if (this.chartInstance) {
      this.chartInstance.destroy();
    }

    // Tạo biểu đồ mới
    const chartElement = document.getElementById("lineChart") as HTMLCanvasElement;
    this.chartInstance = new Chart("lineChart", {
      type: "line",
      data: {
        labels: xValues,
        datasets: [{
          fill: false,
          tension: 0,
          backgroundColor: "rgba(0,0,255,1.0)",
          borderColor: "rgba(0,0,255,0.1)",
          data: yValues
        }]
      },
      options: {
        plugins: {
          title: { // Thêm tiêu đề vào đây
            display: true,
            text: `Doanh thu từ ${startDate.toISOString().split('T')[0]} đến ${endDate.toISOString().split('T')[0]}`,
            position: 'top',
            font: { // Cấu hình font cho tiêu đề
              size: 24, // Kích thước font
              family: 'Arial', // (Tùy chọn) Kiểu chữ
              weight: 'normal', // (Tùy chọn) Độ đậm của chữ
              lineHeight: 1.2 // (Tùy chọn) Chiều cao dòng
            },
            color: '#333' // Màu chữ
          },
          legend: {
            display: false // Đưa legend vào trong plugins
          }
        },
        scales: {
          x: {
            title: {
              display: true,
              text: 'Ngày', // Nhãn cho trục X
              font: {
                size: 14,
                family: 'Arial',
              },
              color: '#333' // Màu chữ cho nhãn trục X
            }, 
            // min: startDate.toString(),
            // max: endDate.toString()

          },
          y: {
            title: {
              display: true,
              text: 'Đồng (VND)', // Nhãn cho trục Y
              font: {
                size: 14,
                family: 'Arial'
              },
              color: '#333' // Màu chữ cho nhãn trục Y
            },
            min: 0,
            max: Math.max(...yValues) + 1000000 

          }
        }
      }
    });
  }
}
