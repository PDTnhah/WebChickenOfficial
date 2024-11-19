package com.A.GA.controller;

import com.A.GA.Service.ComBoAdminService;
import com.A.GA.Service.LoginService;
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

    // view ComBo của Admin
    @GetMapping("/AdminHomeComBo")
    public String adminHome(Model model){
        model.addAttribute("Image", loginService.image());
        return "ComBoAdmin";
    }
    @GetMapping("/addComBo")
    public String addComBo(Model model){
        return "AddComBo";
    }
    @PostMapping("/addComBo")
    public RedirectView addComBo(@RequestParam ("nameComBo") String nameComBo,
                                 @RequestParam("price") int price,
                                 @RequestParam ("describe")String describe,
                                 @RequestParam ("image") String file,
                                 @RequestParam ("category") String category
                                 ){
        comBoAdminService.addComBo(nameComBo,price,category,describe,file);
        System.out.println(nameComBo);
        System.out.println(price);
        System.out.println(describe);
        System.out.println(file);
        return new RedirectView("/AdminHomeComBo");

    }
}
