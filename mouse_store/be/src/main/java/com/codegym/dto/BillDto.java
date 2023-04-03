package com.codegym.dto;

public class BillDto {
    private Integer user;
    private String datePurchase;
    private Integer totalMoney;

    public BillDto() {
    }

    public BillDto(Integer user, String datePurchase, Integer totalMoney) {
        this.user = user;
        this.datePurchase = datePurchase;
        this.totalMoney = totalMoney;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(String datePurchase) {
        this.datePurchase = datePurchase;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }
}
