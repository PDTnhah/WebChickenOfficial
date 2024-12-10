package com.A.GA.controller;

import com.A.GA.Service.ComBoUserService;
import com.A.GA.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ComBoUserController {
    @Autowired
    private ComBoUserService comBoUserService;
    @Autowired
    private LoginService loginService;

    // hiện view của trang ComBo
    @GetMapping("/UserHomeComBo")
    public String homeComBo(Model model){
        model.addAttribute("imageKongPhuong", loginService.image());
        model.addAttribute("allComBo" , comBoUserService.getAllComBo());
        return "ComBoUser";
    }

//    thanh tìm kiếm của trang combo
    @PostMapping("/searchBoxComBo")
    public String search(@RequestParam("searchBox") String searchBox, Model model ){
        model.addAttribute("allComBo", comBoUserService.searchBoxComBo(searchBox));
        model.addAttribute("imageKongPhuong", loginService.image());
        return "ComBoUser";
    }

}
