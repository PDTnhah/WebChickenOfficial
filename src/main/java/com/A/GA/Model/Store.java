package com.A.GA.Model;

import com.A.GA.Repository.StoreRepository;

public class Store {
    private int Auto_increment = StoreRepository.tableStore.size();
    private int maStore;
    private int idUserAdmin;
    private int idProduct;
    private String nameStore;

    public Store( int idUserAdmin, int idProduct, String nameStore) {
        this.maStore = Auto_increment++;
        this.idUserAdmin = idUserAdmin;
        this.idProduct = idProduct;
        this.nameStore = nameStore;
    }

    public int  getMaStore() {
        return maStore;
    }

    public void setMaStore(int maStore) {
        this.maStore = maStore;
    }

    public int getIdUserAdmin() {
        return idUserAdmin;
    }

    public void setIdUserAdmin(int idUserAdmin) {
        this.idUserAdmin = idUserAdmin;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }
}
