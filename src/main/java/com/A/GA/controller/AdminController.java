package com.A.GA.controller;

import com.A.GA.Service.AdminService;
import com.A.GA.Service.ComBoAdminService;
import com.A.GA.Service.ProductService;
import com.A.GA.Service.StoreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @Autowired
    public AdminService adminService;
    @Autowired
    private ProductService serviceProduct;
    @Autowired
    private StoreService serviceStore;

    @Autowired
    ComBoAdminService comBoAdminService;

//    public AdminController(ProductService serviceProduct, StoreService serviceStore) {
//        this.serviceProduct = serviceProduct;
//        this.serviceStore = serviceStore;
//    }

    // gửi thông tin khách haàng order về database của admin
//    @PostMapping("/NotificationToAdmin")
//    public String thongbao(){
//        adminService.notification();
//        return "stateOrder";
//    }
    // đây là view của trang ADMIN
    @GetMapping("/homeAdmin")
    public String homeAdmin(Model model, HttpSession session){
        model.addAttribute("Image",serviceProduct.getImage());
        int idUserAdmin = (int)session.getAttribute("idUser");
        int maStore = serviceStore.getMaStore(idUserAdmin);
        model.addAttribute("ListProductAdmin", serviceProduct.getALLProductAdmin(maStore));
        model.addAttribute("ListComBoAdmin",comBoAdminService.getAllComBoAdmin(maStore));
        return "homeAdmin";
    }
    @PostMapping("/searchBoxAdmin")
    public String search(@RequestParam("searchBox") String searchBox, Model model, HttpSession session ){
        int idUserAdmin = (int)session.getAttribute("idUser");
        int maStore = serviceStore.getMaStore(idUserAdmin);
        model.addAttribute("ListComBoAdmin", comBoAdminService.searchBoxHomeAdmin(searchBox,maStore));
        model.addAttribute("ListProductAdmin", serviceProduct.searchBoxHomeAdmin(searchBox,maStore));
//        model.addAttribute("imageKongPhuong", loginService.image());
        return "homeAdmin";
    }

//    thông tin bảng orderAdmin
    @GetMapping("/AdminHomeComBo")
    public String adminHome(Model model,HttpSession session){
        int idAdmincurrent = (int)session.getAttribute("idUser");
        int maStore = serviceStore.getMaStore(idAdmincurrent);
        model.addAttribute("ListOrder", adminService.getProductOfOneStore(maStore));
        return "orderAdmin";
    }
//    GetMapping("/thongTinChiTietKhachHang")
//    public@

}
