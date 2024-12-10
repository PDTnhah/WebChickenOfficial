package com.A.GA.controller;

import com.A.GA.Model.AddressCustomer;
import com.A.GA.Model.ComBo;
import com.A.GA.Model.ProductChicken;
import com.A.GA.Repository.ProductRepository;
import com.A.GA.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;

@Controller
public class orderController {
    @Autowired
    OrderService orderService;
    @Autowired
    ProductRepository productRepository;
    @PostMapping("/StateOrder")
    public RedirectView HomeStateOrder(Model model, @RequestParam ("hoTen") String hoten,
                                       @RequestParam("phoneNumber") String phoneNumber,
                                       @RequestParam("address") String address,
                                       @RequestParam("transport") String transport,
                                       @RequestParam ("paymentMethod") String paymentMethod,
                                       @RequestParam ("note") String note
                                 ){
        orderService.addCustomerAdmin(hoten,phoneNumber,address,transport,paymentMethod,note);
        AddressCustomer addressCustomer=  orderService.getByIdCustomer();
        orderService.addOrderAdmin(hoten, productRepository.sumProduct() , LocalDateTime.now(),addressCustomer,ProductRepository.tableOrder,ProductRepository.tableOrderComBo,"preparing");
        return new RedirectView("/stateOrder1");
    }
    @GetMapping("/StateOrder1")
    public String HomeState(){
        return "stateOrder";
    }
    @GetMapping("/ConfirmOrder")
    public String confirmOrder(){
        return "confirmOrder";
    }

}
