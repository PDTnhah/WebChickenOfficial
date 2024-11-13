package com.A.GA.Model;

import java.util.Date;

public class History {
    private int id;
    private String nameProduct;
    private Date dateTime;
    private int amount;

    public History(int id, String nameProduct, Date dateTime, int amount) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.dateTime = dateTime;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
