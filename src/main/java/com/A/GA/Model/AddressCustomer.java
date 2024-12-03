package com.A.GA.Model;

public class AddressCustomer {
    private int id;
    private String Name;
    private String phoneNumber;
    private String address;
    private String transport;
    private String note;
    private int idProduct;

    public AddressCustomer(int id, String name, String phoneNumber, String address, String transport, String note, int idProduct) {
        this.id = id;
        Name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.transport = transport;
        this.note = note;
        this.idProduct = idProduct;
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

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}
