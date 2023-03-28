package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.model.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProduct(String name);

    Product findProductById(Integer id);

    Page<Product> showListProduct(String search, Pageable pageable);

    void createProduct(Product product);

    Product findById(Integer id);

    void deleteProduct(Integer id);

    void editProduct(Product product);
}
