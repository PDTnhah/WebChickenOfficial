import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AdminRoutingModule } from './admin-routes';
import { AdminComponent } from './admin.component';
import { OrderAdminComponent } from './order/order.admin.component';
import { DetailOrderAdminComponent } from './detail-order/detail.order.admin.component';
import { SalesRevenueComponent } from './sales-revenue/sales-revenue.component';

@NgModule({
  imports: [
    CommonModule,
    AdminRoutingModule,
    RouterModule,
    // Import các thành phần standalone
    AdminComponent,
    OrderAdminComponent,
    DetailOrderAdminComponent,
    SalesRevenueComponent
  ],
  declarations: [
    
  ]
})
export class AdminModule { }
