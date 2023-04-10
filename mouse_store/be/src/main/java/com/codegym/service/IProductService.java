package com.codegym.service;

import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Product findProductById(Integer id);

    Page<Product> showListProduct(String search, Pageable pageable);

    void createProduct(Product product);

    Product findById(Integer id);

    void deleteProduct(Integer id);

    void editProduct(Product product);
}
