package com.codegym.dto;

public class BillDto {
    private Integer user;
    private int quantity;
    private Integer product;

    public BillDto() {
    }

    public BillDto(Integer user, int quantity, Integer product) {
        this.user = user;
        this.quantity = quantity;
        this.product = product;
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
}
