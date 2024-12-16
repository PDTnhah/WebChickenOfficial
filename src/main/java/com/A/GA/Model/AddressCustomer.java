package com.A.GA.Model;

import com.A.GA.Repository.CustomerRepository;
import com.A.GA.Repository.OrderRepository;

public class AddressCustomer {
    private static int maIdCount = CustomerRepository.tableCuntomer.size(); // tự động tăng id
    private int id;
//    private int maStore;
    private String Name;
    private String phoneNumber;
    private String address;
    private String transport;
    private String note;
    private String paymentMethod;

    public AddressCustomer( String name, String phoneNumber, String address, String transport, String note, String paymentMethod) {
        this.id = maIdCount++;
        Name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.transport = transport;
        this.note = note;
        this.paymentMethod = paymentMethod;
    }

    public AddressCustomer(int id, String name, String phoneNumber, String address, String transport, String note, String paymentMethod) {
        this.id = id;
        Name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.transport = transport;
        this.note = note;
        this.paymentMethod = paymentMethod;
    }

    public static int getMaIdCount() {
        return maIdCount;
    }

    public static void setMaIdCount(int maIdCount) {
        AddressCustomer.maIdCount = maIdCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}