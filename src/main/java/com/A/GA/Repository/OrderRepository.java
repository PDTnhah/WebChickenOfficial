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
    public static int idOrder = 0;
    public static List<orderAdmin> tableOrderAdmin = new ArrayList<>();
    public OrderRepository() {
        // tạo sẵn các sản phẩm
          tableOrderAdmin.add( new orderAdmin(1, "trần thanh hằng", 1000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare"));
          tableOrderAdmin.add( new orderAdmin(3, "trần thanh hằng", 3000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare"));
//        tableProduct.add( new ProductChicken(1,"Burger Tôm" , "chiên" , 2000,tableImage.get(1)));
//        tableProduct.add( new ProductChicken(2,"Burger Gà Quay Flava" , "hấp" , 2000,tableImage.get(2)));
//        tableProduct.add( new ProductChicken(3,"Cơm Phi-lê Gà Quay" , "hấp" , 2000,tableImage.get(3)));
//        tableProduct.add( new ProductChicken(4,"Com Gà Teriyaki" , "hấp" , 2000,tableImage.get(4)));
//        tableProduct.add( new ProductChicken(5,"Mì Ý Gà Viên" , "hấp" , 2000,tableImage.get(5)));
//
////            tạo sẳn ở order
//        tableOrder.add(new ProductChicken(1,"gà chiên", "chiên",1000,tableImage.get(1)));
    }
    public void addCustomerAdmon(int id,String hoten, String phoneNumber, String address, String transport, String paymentMethod, String note) {
        AddressCustomer newCustomer = new AddressCustomer(id,hoten,phoneNumber,address,transport,note,paymentMethod);
        CustomerRepository.tableCuntomer.add(newCustomer);
        idOfCustomer = id;
    }

    public AddressCustomer getByIdCustomer(int id) {
        for (AddressCustomer addressCustomer : CustomerRepository.tableCuntomer){
            if (addressCustomer.getId() == idOfCustomer){
                return addressCustomer;
            }
        }
        return null;
    }

    public void addOrderAdmin(int idUser,String hoten, double sumPrice, LocalDateTime timeNow, AddressCustomer addressCustomer, List<ProductChicken> tableOrder, List<ComBo> tableOrderComBo, String preparing) {
        orderAdmin orderAdminNew = new orderAdmin(idUser,hoten,sumPrice,timeNow,addressCustomer,tableOrder,tableOrderComBo,preparing);
        tableOrderAdmin.add(orderAdminNew);
        idOrder = orderAdminNew.getMaOrder();
    }
    public orderAdmin getByIdOrder(){
        for (orderAdmin orderAdmin : tableOrderAdmin){
            if(orderAdmin.getMaOrder() == idOrder){
                return orderAdmin;
            }
        }
        return  null;
    }


    public List<orderAdmin> getHistory(int idUser, int maOrder) {
        List<orderAdmin> orderAdminHistory = new ArrayList<>();
        for (orderAdmin orderAdmin: tableOrderAdmin){
            if (orderAdmin.getIdUser() == idUser && orderAdmin.getMaOrder() == maOrder){
                orderAdminHistory.add(orderAdmin);
            }
        }
        return orderAdminHistory;
    }
}



