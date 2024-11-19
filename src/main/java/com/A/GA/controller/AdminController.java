package com.A.GA.controller;

import com.A.GA.Model.ProductChicken;
import com.A.GA.Service.AdminService;
import com.A.GA.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    @Autowired
    public AdminService adminService;
    @Autowired
    private ProductService serviceProduct;

    // gửi thông tin khách haàng order về database của admin
    @PostMapping("/NotificationToAdmin")
    public String thongbao(){
        adminService.notification();
        return "stateOrder";
    }
    // đây là view của trang ADMIN
    @GetMapping("/homeAdmin")
    public String homeAdmin( Model model){
        model.addAttribute("Image",serviceProduct.getImage());
        return "homeAdmin";
    }
}
