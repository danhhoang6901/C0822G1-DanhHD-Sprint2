package com.codegym.dto;

import com.codegym.model.Product;

public class ImageDto {
    private Integer id;
    private String url;
    private Integer product;

    public ImageDto() {
    }

    public ImageDto(Integer id, String url, Integer product) {
        this.id = id;
        this.url = url;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }
}
