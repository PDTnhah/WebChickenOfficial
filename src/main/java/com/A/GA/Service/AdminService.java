package com.A.GA.Service;

import com.A.GA.Model.ProductChicken;
import com.A.GA.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    public AdminRepository adminRepository;
    public void notification() {
        adminRepository.notification();
    }
}
