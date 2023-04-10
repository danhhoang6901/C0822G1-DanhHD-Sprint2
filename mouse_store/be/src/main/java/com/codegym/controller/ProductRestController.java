package com.codegym.controller;

import com.codegym.dto.ImageDto;
import com.codegym.dto.ProductDto;
import com.codegym.model.Image;
import com.codegym.model.Product;
import com.codegym.model.Size;
import com.codegym.service.IImageService;
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

    @Autowired
    private IImageService imageService;

    @GetMapping("")
    public ResponseEntity<Page<Product>> getAllProduct(@RequestParam(name = "name", defaultValue = "", required = false)
                                                               String name, @PageableDefault(size = 4) Pageable pageable) {
        Page<Product> products = productService.showListProduct(name, pageable);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("find/{id}")
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
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("create/img")
    public ResponseEntity<?> createImage(@RequestBody @Validated ImageDto imageDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_MODIFIED);
        }
        Image image = new Image();
        BeanUtils.copyProperties(imageDto, image);
        Product product = productService.findById(imageDto.getProduct());
        image.setProduct(product);
        imageService.createImage(image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Integer id) {
        Product product = productService.findProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<?> editProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult,
                                         @PathVariable("id") Integer id) {
        Product product = productService.findProductById(id);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_MODIFIED);
        }
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        BeanUtils.copyProperties(productDto, product);
        productService.editProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
