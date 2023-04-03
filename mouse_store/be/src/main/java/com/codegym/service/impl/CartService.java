package com.codegym.service.impl;

import com.codegym.model.Cart;
import com.codegym.model.Product;
import com.codegym.model.User;
import com.codegym.repository.ICartRepository;
import com.codegym.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public Cart getCart(Integer user) {
        return cartRepository.getUser(user);
    }

    @Override
    public boolean addCart(Product product, Integer id, String size) {
        return cartRepository.existsByProductIdAndUserIdAndSize(product.getId(), id, size);
    }

    @Override
    public Cart findByProductIdAndUserIdAndSize(Product product, Integer id, String size) {
        return cartRepository.findByProductIdAndUserIdAndSize(product.getId(), id, size);
    }

    @Override
    public void createCart(Cart cart1) {
        cartRepository.save(cart1);
    }

    @Override
    public List<Cart> getCartByUserId(String id) {
        return cartRepository.getCartByUserId(id);
    }

    @Override
    public Cart findCartById(Integer id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProducInCart(Integer id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<Cart> findAllByUser(User byId) {
        return cartRepository.findAllByUser(byId);
    }

    @Override
    public void deleteCartByIdUser(Integer user) {
        cartRepository.deleteCartByIdUser(user);
    }


}
