package com.codegym.dto;

import com.codegym.model.Category;
import com.codegym.model.Origin;
import com.codegym.model.Style;
import com.codegym.model.Trademark;

public class ProductDto {
    private Integer id;
    private String codeProduct;
    private String name;
    private String image;
    private String color;
    private String description;
    private int quantity;
    private Double price;
    private String material;
    private String washingInstructions;
    private boolean flagDelete = false;
    private Style style;
    private Origin origin;
    private Trademark trademark;
    private Category category;

    public ProductDto() {
    }

    public ProductDto(Integer id, String codeProduct, String name, String image, String color, String description, int quantity, Double price, String material, String washingInstructions, boolean flagDelete, Style style, Origin origin, Trademark trademark, Category category) {
        this.id = id;
        this.codeProduct = codeProduct;
        this.name = name;
        this.image = image;
        this.color = color;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.material = material;
        this.washingInstructions = washingInstructions;
        this.flagDelete = flagDelete;
        this.style = style;
        this.origin = origin;
        this.trademark = trademark;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getWashingInstructions() {
        return washingInstructions;
    }

    public void setWashingInstructions(String washingInstructions) {
        this.washingInstructions = washingInstructions;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Trademark getTrademark() {
        return trademark;
    }

    public void setTrademark(Trademark trademark) {
        this.trademark = trademark;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
