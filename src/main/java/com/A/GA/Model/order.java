package com.A.GA.Model;

import java.time.LocalDateTime;
import java.util.List;

public class order {
    private int maOrder;
    private String nameCustomer;
    private Long sumMoney;
    private LocalDateTime time;
    private String deliveryAddress;
    private String paymentMethod;
    private List<String> products;
    private String status;

    public order(int maOrder, String nameCustomer, Long sumMoney, LocalDateTime time, String deliveryAddress, String paymentMethod, List<String> products, String status) {
        this.maOrder = maOrder;
        this.nameCustomer = nameCustomer;
        this.sumMoney = sumMoney;
        this.time = time;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
        this.products = products;
        this.status = status;
    }

    public int getMaOrder() {
        return maOrder;
    }

    public void setMaOrder(int maOrder) {
        this.maOrder = maOrder;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public Long getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Long sumMoney) {
        this.sumMoney = sumMoney;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
