package com.codegym.service;

import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<Product> getAllProduct(String name);

    Product findProductById(Integer id);

    Page<Product> showListProduct(String search, Pageable pageable);

    void createProduct(Product product);
}
