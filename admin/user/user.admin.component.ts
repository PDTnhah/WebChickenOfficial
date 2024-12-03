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

@Component({
  selector: 'app-user-admin',    
  templateUrl: './user.admin.component.html',
  styleUrl: './user.admin.component.scss',
  standalone: true,
  imports: [   
    CommonModule,
    FormsModule,
  ]
})
export class UserAdminComponent implements OnInit{
  users: User[] = [];    
    currentPage: number = 0;
    itemsPerPage: number = 12;
    pages: number[] = [];
    totalPages:number = 0;
    visiblePages: number[] = [];
    keyword:string = "";
    localStorage?:Storage;
    private toastr = inject(ToastrService);
    constructor(
      private userService: UserService,
      private router: Router,
      private route: ActivatedRoute,
      private location: Location,
      @Inject(DOCUMENT) private document: Document
    ) {
      
      this.localStorage = document.defaultView?.localStorage;
    }
  ngOnInit(): void {
    debugger
      this.currentPage = Number(this.localStorage?.getItem('currentUserAdminPage')) || 0; 
      this.getAllUsers(this.keyword, this.currentPage, this.itemsPerPage);  
  }
  searchUsers() {
    this.currentPage = 0;
    this.itemsPerPage = 12;
    //Mediocre Iron Wallet
    debugger
    this.getAllUsers(this.keyword.trim(), this.currentPage, this.itemsPerPage);
  }
  getAllUsers(keyword : string, page: number, limit: number) {
    debugger
    this.userService.getAllUsers(keyword, page, limit).subscribe({
      next: (response: any) => {
        debugger
        this.users = response.users
        this.totalPages = response.totalPages;
        this.visiblePages = this.generateVisiblePageArray(this.currentPage, this.totalPages);
      },
      complete: () => {
        debugger;
      },
      error: (error: any) => {
        debugger;
        console.error('Error fetching products:', error);
      }
    });    
  }
  blockOrEnable(userId : number) {
    this.userService.blockOrEnable(userId).subscribe({
      next: (respone: any) => {
        debugger
        if(respone.is_active === true) {
          this.toastr.success(`Bạn đã mở tài khoản cho ${respone.fullname}`);
          
        }
        else {
          this.toastr.success(`Bạn đã khóa tài khoản của ${respone.fullname}`);
        }
    },
      complete: () => {
        this.getAllUsers("" , 0 , 12);
      },
      error: (err: any) => {
        alert(`${JSON.stringify(err.error)}`);
      }
    })
  }
  onPageChange(page: number) {
    debugger;
    this.currentPage = page < 0 ? 0 : page;
    this.localStorage?.setItem('currentUserAdminPage', String(this.currentPage));     
    this.getAllUsers(this.keyword, this.currentPage, this.itemsPerPage);
  }

  generateVisiblePageArray(currentPage: number, totalPages: number): number[] {
    const maxVisiblePages = 5;
    const halfVisiblePages = Math.floor(maxVisiblePages / 2);
  
    let startPage = Math.max(currentPage - halfVisiblePages, 1);
    let endPage = Math.min(startPage + maxVisiblePages - 1, totalPages);
  
    if (endPage - startPage + 1 < maxVisiblePages) {
      startPage = Math.max(endPage - maxVisiblePages + 1, 1);
    }
  
    return new Array(endPage - startPage + 1).fill(0)
      .map((_, index) => startPage + index);
  }
}