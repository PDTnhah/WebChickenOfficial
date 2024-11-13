package com.A.GA.controller;

import com.A.GA.Service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

public class HistoryController {
    @Autowired
    private HistoryService historyService;
    @PostMapping("/allProductHistory")
    public RedirectView history(@RequestParam("id") int id,
                                @RequestParam("nameProduct") String nameProduct,
                                @DateTimeFormat("dateTime") Date dateTime,
                                @RequestParam("amount") int amount) {

        return new RedirectView("/getAllHistory");
    }
    @GetMapping("/getAllHistory")
    public String history (Model model){
        model.addAttribute("listHistory", historyService.getAllHistory());
    }

}
