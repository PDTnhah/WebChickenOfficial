package com.A.GA.Model;

import java.time.LocalDateTime;
import java.util.List;

public class orderAdmin {
    private static int maOrderCount = 0; // tự động tăng id
    private int maOrder;
    private int idUser;
    private String nameCustomer;
    private double sumMoney;
    private LocalDateTime time;
    private AddressCustomer InforDetailed; // thông tin chi tiết mà khách hàng muốn
    private List<ProductChicken> products1; // link nhiều sản phẩm
    private  List<ComBo> products2 ;// Link nhiều sản phẩm combo
    private String status; // đây phải là 1 trường lựa trọn gồm các option ( đang chuẩn bị, đã giao cho ship)

    public orderAdmin(int idUser, String nameCustomer, double sumMoney, LocalDateTime time, AddressCustomer inforDetailed, List<ProductChicken> products1, List<ComBo> products2, String status) {
        this.maOrder = maOrderCount;
        this.idUser=idUser;
        this.nameCustomer = nameCustomer;
        this.sumMoney = sumMoney;
        this.time = time;
        InforDetailed = inforDetailed;
        this.products1 = products1;
        this.products2 = products2;
        this.status = status;
    }

    public orderAdmin() {
    }

    public static int getMaOrderCount() {
        return maOrderCount;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public static void setMaOrderCount(int maOrderCount) {
        orderAdmin.maOrderCount = maOrderCount;
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

    public double getSumMoney() {
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

    public AddressCustomer getInforDetailed() {
        return InforDetailed;
    }

    public void setInforDetailed(AddressCustomer inforDetailed) {
        InforDetailed = inforDetailed;
    }

    public List<ProductChicken> getProducts1() {
        return products1;
    }

    public void setProducts1(List<ProductChicken> products1) {
        this.products1 = products1;
    }

    public List<ComBo> getProducts2() {
        return products2;
    }

    public void setProducts2(List<ComBo> products2) {
        this.products2 = products2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}