package com.A.GA.Repository;

import com.A.GA.Model.AddressCustomer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    public static List<AddressCustomer> tableCuntomer = new ArrayList<>();

    public CustomerRepository() {
        tableCuntomer.add(new AddressCustomer(1,"tuyền","0353616131","yên phong","be", "thêm cơm","tiền mặt"));
        tableCuntomer.add(new AddressCustomer(0,"đạt","0353616131","yên phong","be", "thêm cơm","tiền mặt"));
        tableCuntomer.add(new AddressCustomer(2,"đưc","0353616131","yên phong","be", "thêm cơm","tiền mặt"));
        tableCuntomer.add(new AddressCustomer(3,"thảo","0353616131","yên phong","be", "thêm cơm","tiền mặt"));
    }
}
