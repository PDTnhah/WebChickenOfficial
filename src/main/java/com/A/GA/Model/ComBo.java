package com.A.GA.Model;

import com.A.GA.Repository.ComBoRepository;

public class ComBo {
    private static int maComBoCount= ComBoRepository.tableComBo.size(); // tự động tăng id
    private int maComBo;
    private String nameComBo;
    private int price;
    private String image;
    private String describe;
    private String category;
    private int maStore;

    public ComBo(String nameComBo, int price, String image, String describe, String category) {
        this.maComBo = maComBoCount++;
        this.nameComBo = nameComBo;
        this.price = price;
        this.image = image;
        this.describe = describe;
        this.category = category;
    }

    public ComBo( String nameComBo, int price, String image, String describe, String category, int maStore) {
        this.maComBo = maComBoCount++;
        this.nameComBo = nameComBo;
        this.price = price;
        this.image = image;
        this.describe = describe;
        this.category = category;
        this.maStore = maStore;
    }

    public static int getMaComBoCount() {
        return maComBoCount;
    }

    public static void setMaComBoCount(int maComBoCount) {
        ComBo.maComBoCount = maComBoCount;
    }

    public int getMaStore() {
        return maStore;
    }

    public void setMaStore(int maStore) {
        this.maStore = maStore;
    }

    public int getMaComBo() {
        return maComBo;
    }

    public void setMaComBo(int maComBo) {
        this.maComBo = maComBo;
    }

    public String getNameComBo() {
        return nameComBo;
    }

    public void setNameComBo(String nameComBo) {
        this.nameComBo = nameComBo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
