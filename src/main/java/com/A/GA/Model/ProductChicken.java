package com.A.GA.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProductChicken {
    private int id;
//    private int idUser;

    private String nameProduct;
    private String category;
    private double price;
    private String image;
    private int maStore;
    public ProductChicken(int id, String nameProduct, String category, long price, String image) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    public ProductChicken(int id, String nameProduct, String category, double price, String image, int maStore) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.category = category;
        this.price = price;
        this.image = image;
        this.maStore = maStore;
    }

    public int getMaStore() {
        return maStore;
    }

    public void setMaStore(int maStore) {
        this.maStore = maStore;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
