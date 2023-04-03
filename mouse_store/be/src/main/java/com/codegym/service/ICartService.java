package com.codegym.service;

import com.codegym.model.Cart;
import com.codegym.model.Product;
import com.codegym.model.User;

import java.util.List;

public interface ICartService {
    Cart getCart(Integer user);

    boolean addCart(Product product, Integer id, String size);

    Cart findByProductIdAndUserIdAndSize(Product product, Integer id, String size);

    void createCart(Cart cart1);

    List<Cart> getCartByUserId(String id);

    Cart findCartById(Integer id);

    void deleteProducInCart(Integer id);

    List<Cart> findAllByUser(User byId);

    void deleteCartByIdUser(Integer user);
}
