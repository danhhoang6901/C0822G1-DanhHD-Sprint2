package com.codegym.controller;

import com.codegym.dto.ProductDto;
import com.codegym.model.Product;
import com.codegym.model.Role;
import com.codegym.model.Size;
import com.codegym.service.IProductService;
import com.codegym.service.ISizeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("products")
public class ProductRestController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ISizeService sizeService;

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProduct(@RequestParam(name = "name", defaultValue = "", required = false) String name) {
        List<Product> products = productService.getAllProduct(name);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") Integer id) {
        Product product = productService.findProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<Page<Product>> showListProduct(@RequestParam(defaultValue = "", required = false) String search,
                                                         @PageableDefault(size = 3) Pageable pageable) {
        Page<Product> products = productService.showListProduct(search, pageable);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<?> createProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_MODIFIED);
        }
        Product product = new Product();
        Set<Size> sizes = sizeService.getAllSize();
        BeanUtils.copyProperties(productDto, product);
        product.setSizes(sizes);
        productService.createProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
