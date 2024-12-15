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
          tableOrderAdmin.add( new orderAdmin(2, "trần thanh hằng", 1000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare",1));
          tableOrderAdmin.add( new orderAdmin(1, "trần thanh hằng", 1000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare",1));
          tableOrderAdmin.add( new orderAdmin(3, "trần thanh hằng", 1000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare",1));
          tableOrderAdmin.add( new orderAdmin(4, "trần thanh hằng", 1000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare",1));
          tableOrderAdmin.add( new orderAdmin(5, "trần thanh hằng", 1000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare",1));
          tableOrderAdmin.add( new orderAdmin(6, "trần thanh hằng", 1000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare",1));
          tableOrderAdmin.add( new orderAdmin(7, "trần thanh hằng", 1000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare",1));
          tableOrderAdmin.add( new orderAdmin(8, "trần thanh hằng", 1000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare",1));
          tableOrderAdmin.add( new orderAdmin(9, "trần thanh hằng", 1000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare",1));
          tableOrderAdmin.add( new orderAdmin(10, "trần thanh hằng", 1000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare",1));
          tableOrderAdmin.add( new orderAdmin(11, "trần thanh hằng", 1000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare",1));
          tableOrderAdmin.add( new orderAdmin(12, "trần thanh hằng", 1000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare",1));
//          tableOrderAdmin.add( new orderAdmin(1, "trần thanh hằng", 3000,LocalDateTime.now(), getByIdCustomer(1),ProductRepository.tableOrder, ProductRepository.tableOrderComBo,"prepare",2));
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

    public void addOrderAdmin(int idUser,String hoten, double sumPrice, LocalDateTime timeNow, AddressCustomer addressCustomer, List<ProductChicken> tableOrder, List<ComBo> tableOrderComBo, String preparing, int maStore) {
        orderAdmin orderAdminNew = new orderAdmin(idUser,hoten,sumPrice,timeNow,addressCustomer,tableOrder,tableOrderComBo,preparing,maStore);
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


    public List<orderAdmin> getHistory(int idUser) {
        List<orderAdmin> orderAdminHistory = new ArrayList<>();
        for (orderAdmin orderAdmin: tableOrderAdmin){
            if (orderAdmin.getIdUser() == idUser){
                orderAdminHistory.add(orderAdmin);
            }
        }
        return orderAdminHistory;
    }

    public List<orderAdmin> getproductOfOneStore(int maStore) {
        List<orderAdmin> listProductOfOneStore = new ArrayList<>();
        for (orderAdmin orderAdmin:tableOrderAdmin){
            if ((orderAdmin.getMaStore() == maStore)){
                listProductOfOneStore.add(orderAdmin);
            }

        }
        return listProductOfOneStore;
    }
}



