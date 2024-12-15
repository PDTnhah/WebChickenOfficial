package com.A.GA.Service;

import com.A.GA.Model.AddressCustomer;
import com.A.GA.Model.ComBo;
import com.A.GA.Model.ProductChicken;
import com.A.GA.Model.orderAdmin;
import com.A.GA.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addCustomerAdmin(int id,String hoten, String phoneNumber, String address, String transport, String paymentMethod, String note) {
        orderRepository.addCustomerAdmon(id,hoten,phoneNumber,address,transport,paymentMethod,note);
    }

    public AddressCustomer getByIdCustomer(int id) {
        return orderRepository.getByIdCustomer(id);
    }

    public void addOrderAdmin(int idUser,String hoten, double sumPrice, LocalDateTime timeNow, AddressCustomer addressCustomer, List<ProductChicken> tableOrder, List<ComBo> tableOrderComBo, String preparing, int maStore) {

        orderRepository.addOrderAdmin(idUser,hoten, sumPrice, timeNow,addressCustomer,tableOrder,tableOrderComBo,preparing,maStore);
    }

    public orderAdmin getByIdOrder() {
        return orderRepository.getByIdOrder();
    }

    public List<orderAdmin> getHistory(int idUser) {
        return  orderRepository.getHistory(idUser);
    }
}
