package com.A.GA.controller;

import com.A.GA.Service.ComBoAdminService;
import com.A.GA.Service.LoginService;
import com.A.GA.Service.StoreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ComBoAdminController {
    @Autowired
    private ComBoAdminService comBoAdminService;
    @Autowired
    private LoginService loginService;

    @Autowired
    private StoreService storeService;

    // view ComBo của Admin
//    @GetMapping("/AdminHomeComBo")
//    public String adminHome(Model model){
//        model.addAttribute("Image", loginService.image());
//        return "orderAdmin";
//    }
    @GetMapping("/addComBo")
    public String addComBo(Model model){
        return "AddComBo";
    }
    @PostMapping("/addComBo")
    public RedirectView addComBo(@RequestParam ("nameComBo") String nameComBo,
                                 @RequestParam("price") int price,
                                 @RequestParam ("describe")String describe,
                                 @RequestParam ("compressedImage") String image1,
                                 @RequestParam ("category") String category,
                                 HttpSession session
                                 ){
        int idUserAdmin = (int)session.getAttribute("idUser");
        int maStore = storeService.getMaStore(idUserAdmin);
        String image = image1.split(",")[2];
        comBoAdminService.addComBo(nameComBo,price,category,describe,image,maStore);
        System.out.println(nameComBo);
        System.out.println(price);
        System.out.println(describe);
        System.out.println(image);
        return new RedirectView("/AdminHomeComBo");

    }
}
