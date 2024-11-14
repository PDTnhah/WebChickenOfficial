package com.project.shopapp.Controllers;

import com.project.shopapp.dtos.EmailRequestDTO;
import com.project.shopapp.services.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("${api.prefix}/users")
//@Validated
//Dependency Injection
@RequiredArgsConstructor
@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public String sendEmail(@Valid @RequestBody EmailRequestDTO emailRequest) {
        emailService.sendSimpleEmail(emailRequest.getToEmail(), emailRequest.getSubject(), emailRequest.getBody());
        return "Email send to " + emailRequest.getToEmail();
    }
    
}
