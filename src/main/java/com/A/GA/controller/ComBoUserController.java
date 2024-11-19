package com.A.GA.controller;

import com.A.GA.Service.ComBoUserService;
import com.A.GA.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
