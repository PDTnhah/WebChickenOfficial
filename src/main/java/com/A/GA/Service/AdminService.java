package com.A.GA.Service;

import com.A.GA.Model.ProductChicken;
import com.A.GA.Model.orderAdmin;
import com.A.GA.Repository.AdminRepository;
import com.A.GA.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    public AdminRepository adminRepository;

    @Autowired
    public OrderRepository orderRepository;

    public List<orderAdmin> getProductOfOneStore(int maStore) {
        return orderRepository.getproductOfOneStore(maStore);
    }
//    public void notification() {
//        adminRepository.notification();
//    }
}
