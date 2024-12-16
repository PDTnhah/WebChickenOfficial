package com.A.GA.controller;

import com.A.GA.Model.AddressCustomer;
import com.A.GA.Model.ComBo;
import com.A.GA.Model.ProductChicken;
import com.A.GA.Model.orderAdmin;
import com.A.GA.Repository.ProductRepository;
import com.A.GA.Service.OrderService;
import com.A.GA.Service.StoreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class orderController {
    @Autowired
    OrderService orderService;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    StoreService storeService;
    @PostMapping("/StateOrder")
    public RedirectView HomeStateOrder( RedirectAttributes redirectAttributes, HttpSession session, @RequestParam ("hoTen") String hoten,
                                       @RequestParam("phoneNumber") String phoneNumber,
                                       @RequestParam("address") String address,
                                       @RequestParam("transport") String transport,
                                       @RequestParam ("paymentMethod") String paymentMethod,
                                       @RequestParam ("note") String note
                                 ){
        int idUser = (int)session.getAttribute("idUser");
        orderService.addCustomerAdmin(idUser,hoten,phoneNumber,address,transport,paymentMethod,note);
        AddressCustomer addressCustomer=  orderService.getByIdCustomer(idUser);

//        int maStore = storeService.getMaStore(idUserCurrent);
        int maStore = storeService.getMaStore(UserController.IDPRODUCT);
//        int maStore = UserController.IDPRODUCT;
//        int maStore = productRepository.getMaStoreByIdProduct(UserController.IDPRODUCT);

        // sao lưu tất cả giá trị sang bảng mới mà k phải sao lưu tham chiều
        List<ProductChicken> productTamThoi = new ArrayList<>(ProductRepository.tableOrder);
        List<ComBo> comBoTamThoi = new ArrayList<>(ProductRepository.tableOrderComBo);
        orderService.addOrderAdmin(addressCustomer.getId(),hoten, productRepository.sumProduct() , LocalDateTime.now(),addressCustomer,productTamThoi,comBoTamThoi,"preparing",maStore);
        productRepository.removeAllProductTemporary();
        productRepository.reomoveAllComBoTemporary();
        orderAdmin order = orderService.getByIdOrder();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

// Tách riêng ngày và giờ
        String date = order.getTime().toLocalDate().format(dateFormatter);
        String time = order.getTime().toLocalTime().format(timeFormatter);

// Truyền vào model
        redirectAttributes.addFlashAttribute("date", date);
        redirectAttributes.addFlashAttribute("time", time);
        return new RedirectView("/stateOrder1");
    }


    @GetMapping("/History")
    public String historyOrder (Model model,HttpSession session){
        int idUser = (int)session.getAttribute("idUser");
        model.addAttribute("ListHistoty", orderService.getHistory(idUser));
        return "historyOrder";
    }


    @GetMapping("/stateOrder1")
    public String HomeState(){
        return "stateOrder";
    }
    @GetMapping("/ConfirmOrder")
    public String confirmOrder(){
        return "confirmOrder";
    }


}
