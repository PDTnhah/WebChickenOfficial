package com.A.GA.Repository;

import com.A.GA.Model.AddressCustomer;
import com.A.GA.Model.ComBo;
import com.A.GA.Model.ProductChicken;
import com.A.GA.Model.orderAdmin;
import jakarta.servlet.http.PushBuilder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    public static int idOfCustomer=0;
    public static List<orderAdmin> tableOrderAdmin = new ArrayList<>();

    public void addCustomerAdmon(String hoten, String phoneNumber, String address, String transport, String paymentMethod, String note) {
        AddressCustomer newCustomer = new AddressCustomer(hoten,phoneNumber,address,transport,note,paymentMethod);
        CustomerRepository.tableCuntomer.add(newCustomer);
        idOfCustomer = newCustomer.getId();
    }

    public AddressCustomer getByIdCustomer() {
        for (AddressCustomer addressCustomer : CustomerRepository.tableCuntomer){
            if (addressCustomer.getId() == idOfCustomer){
                return addressCustomer;
            }
        }
        return null;
    }

    public void addOrderAdmin(String hoten, double sumPrice, LocalDateTime timeNow, AddressCustomer addressCustomer, List<ProductChicken> tableOrder, List<ComBo> tableOrderComBo, String preparing) {
        orderAdmin orderAdminNew = new orderAdmin(hoten,sumPrice,timeNow,addressCustomer,tableOrder,tableOrderComBo,preparing);
        tableOrderAdmin.add(orderAdminNew);
    }
}



