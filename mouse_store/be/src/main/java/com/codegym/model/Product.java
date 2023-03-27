package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "style_id", referencedColumnName = "id")
    private Style style;

    @ManyToOne
    @JoinColumn(name = "origin_id", referencedColumnName = "id")
    private Origin origin;

    @ManyToOne
    @JoinColumn(name = "trademark_id", referencedColumnName = "id")
    private Trademark trademark;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_sizes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "sizes_id"))
    @JsonManagedReference
    private Set<Size> sizes;

    public Product() {
    }

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
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

    public void setWashingInstructions(String washingIntructions) {
        this.washingInstructions = washingIntructions;
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
