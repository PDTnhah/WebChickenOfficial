package com.A.GA.Model;

public class ComBo {
    private static int maComBoCount=0;
    private int maComBo;
    private String nameComBo;
    private int price;
    private String image;
    private String describe;
    private String category;

    public ComBo(String nameComBo, int price, String image, String describe, String category) {
        this.maComBo = maComBoCount++;
        this.nameComBo = nameComBo;
        this.price = price;
        this.image = image;
        this.describe = describe;
        this.category = category;
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
