package com.A.GA.Repository;

import com.A.GA.Model.AddressCustomer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    public static List<AddressCustomer> tableCuntomer = new ArrayList<>();

    public CustomerRepository() {
        tableCuntomer.add(new AddressCustomer("tuyen","0353616131","yên phong","be", "thêm cơm","tiền mặt"));
    }
}
