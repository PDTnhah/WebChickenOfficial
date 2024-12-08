package com.A.GA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class orderController {
    @GetMapping("/StateOrder")
    public String HomeStateOrder(Model model){
        return "stateOrder";
    }
    @GetMapping("/ConfirmOrder")
    public String confirmOrder(Model model){
        return "confirmOrder";
    }
}
