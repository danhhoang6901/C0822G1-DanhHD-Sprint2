package com.codegym.controller;

import com.codegym.dto.BillDto;
import com.codegym.dto.CartDto;
import com.codegym.model.*;
import com.codegym.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("cart")
public class CartRestController {
    @Autowired
    private IBillService billService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IBillDetailService billDetailService;

    @GetMapping("{id}")
    public ResponseEntity<List<Cart>> getListCartByUserId(@PathVariable("id") String id) {
        List<Cart> cart = cartService.getCartByUserId(id);
        if (cart.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("addCart")
    public ResponseEntity<?> addBill(@RequestBody CartDto cartDto) {
        Cart cart = new Cart();
        User user = userService.findById(cartDto.getUser());
        Product product = productService.findProductById(cartDto.getProduct());
        BeanUtils.copyProperties(cartDto, cart);
        cart.setUser(user);
        cart.setProduct(product);
        if (cartService.addCart(cart.getProduct(), cart.getUser().getId(), cart.getSize())) {
            Cart cart1 = cartService.findByProductIdAndUserIdAndSize(cart.getProduct(), cart.getUser().getId(), cart.getSize());
            cart1.setQuantity(cart1.getQuantity() + 1);
            cart1.setSize(cart1.getSize());
            cartService.createCart(cart1);
        } else {
            Cart cart1 = new Cart();
            cart1.setQuantity(cartDto.getQuantity());
            cart1.setSize(cartDto.getSize());
            cart1.setProduct(productService.findProductById(cartDto.getProduct()));
            cart1.setUser(userService.findById(cartDto.getUser()));
            cartService.createCart(cart1);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("minus/{id}")
    public ResponseEntity<Cart> minusQuantity(@PathVariable("id") Integer id) {
        Cart cart = cartService.findCartById(id);
        cart.setQuantity(cart.getQuantity() - 1);
        cartService.createCart(cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("plus/{id}")
    public ResponseEntity<Cart> plusQuantity(@PathVariable("id") Integer id) {
        Cart cart = cartService.findCartById(id);
        cart.setQuantity(cart.getQuantity() + 1);
        cartService.createCart(cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Cart> deleteProducInCart(@PathVariable("id") Integer id) {
        Cart cart = cartService.findCartById(id);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cartService.deleteProducInCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("buy")
    public ResponseEntity<?> buy(@RequestBody BillDto billDto) {
        List<Cart> list = cartService.findAllByUser(userService.findById(billDto.getUser()));
        Bill bill = new Bill();
        bill.setDatePurchase(billDto.getDatePurchase());
        bill.setTotalMoney(billDto.getTotalMoney());
        bill.setUser(userService.findById(billDto.getUser()));
        billService.addBill(bill);
        for (int i = 0; i < list.size(); i++) {
            BillDetail billDetail = new BillDetail();
            billDetail.setBill(bill);
            billDetail.setQuantity(list.get(i).getQuantity());
            billDetail.setProduct(list.get(i).getProduct());
            billDetail.setSize(list.get(i).getSize());
            billDetailService.addBillDetail(billDetail);
        }
        cartService.deleteCartByIdUser(billDto.getUser());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
