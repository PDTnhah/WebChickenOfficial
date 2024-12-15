package com.A.GA.controller;

import com.A.GA.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
    //     lưu tạm thời id của sản phẩm khi người dùng nhấn chọn vào đây đang sử dụng trường hợp mk đang thao tác với 1 cửa hàng
    public static int IDPRODUCT =0 ;
    @Autowired
    private ProductService serviceProduct;
    // đưa toàn bộ sản phẩm ra ngoài màn hình home
    @GetMapping("/homeUser")
    public String homeUser(Model model) {
        model.addAttribute("ListProduct", serviceProduct.getProduct());
        model.addAttribute("Image",serviceProduct.getImage());
        return "homeUser";
    }


    // lấy ra toàn bộ danh sách khách hàng đã order
    @GetMapping("/order/product/get")
    public String order(Model model){
        model.addAttribute("listOrderProduct", serviceProduct.getOrderProduct());
        model.addAttribute("ListProductComBo",serviceProduct.getOrderProductComBo());
        model.addAttribute("sumProduct",serviceProduct.sumProduct());
        return "order";
    }

    //    điều hướng khi người dùng nhấn vào 1 sản phẩm bất kỳ
    @PostMapping("/order/product/{id}")
    public RedirectView order(@PathVariable("id") int id){
        serviceProduct.addOrderProduct(id);
        IDPRODUCT = id;
        return  new RedirectView("/order/product/get");
    }

    // sử lý khi người dùng muốn sóa 1 sản phẩm
    @GetMapping("/order/delete")
    public RedirectView OrderDelete(@RequestParam ("id") int id){
        serviceProduct.deleteOrder(id);
        return new RedirectView("/order/product/get");
    }

//     sử lý với trang khi người dùng nhấn chọn sản phẩm combo
    @PostMapping("/order/productComBo/{id}")
    public RedirectView orderComBo(@PathVariable("id") int maComBo){
        serviceProduct.addOrderProductComBo(maComBo);
        return  new RedirectView("/order/product/get");
    }

//    deleteComBo
    @GetMapping("/order/deleteComBo")
    public RedirectView OrderDeleteComBo(@RequestParam ("id") int maComBo){
        serviceProduct.deleteOrderComBo(maComBo);
        return new RedirectView("/order/product/get");
    }


//     sử lý thanh tìm kiếm
    @PostMapping("/searchBox")
    public String search(@RequestParam ("searchBox") String searchBox, Model model ){
        model.addAttribute("ListProduct", serviceProduct.searchBox(searchBox));
        model.addAttribute("Image",serviceProduct.getImage());
        return "homeUser";
    }
}
