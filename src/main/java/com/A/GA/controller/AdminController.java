package com.A.GA.controller;

import com.A.GA.Model.ProductChicken;
import com.A.GA.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    @Autowired
    public AdminService adminService;

    // gửi thông tin khách haàng order về database của admin
    @PostMapping("/NotificationToAdmin")
    public String thongbao(){
        adminService.notification();
        return "stateOrder";
    }
}
