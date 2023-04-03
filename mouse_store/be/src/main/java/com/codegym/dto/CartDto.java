package com.codegym.dto;

public class CartDto {
    private Integer id;
    private Integer user;
    private int quantity;
    private Integer product;
    private String size;
    private double totalMoney;

    public CartDto() {
    }

    public CartDto(Integer id, Integer user, int quantity, Integer product, String size, double totalMoney) {
        this.id = id;
        this.user = user;
        this.quantity = quantity;
        this.product = product;
        this.size = size;
        this.totalMoney = totalMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
