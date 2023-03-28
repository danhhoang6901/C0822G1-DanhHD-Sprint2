package com.codegym.service.impl;

import com.codegym.model.Product;
import com.codegym.model.Size;
import com.codegym.repository.IProductRepository;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> getAllProduct(String name) {
        return productRepository.getAllProduct(name);
    }

    @Override
    public Product findProductById(Integer id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Page<Product> showListProduct(String search, Pageable pageable) {
        return productRepository.showListProduct(search, pageable);
    }

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteProduct(id);
    }

    @Override
    public void editProduct(Product product) {
        productRepository.save(product);
    }

}
