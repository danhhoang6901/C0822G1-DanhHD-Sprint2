package com.codegym.repository;

import com.codegym.model.Cart;
import com.codegym.model.Product;
import com.codegym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "select b.* from bill b " +
            "join payment p on p.id = b.payment_id " +
            "join `user` u on u.id = b.user_id " +
            "where b.user_id =:userId and p.payment_status = false",
            nativeQuery = true)
    Cart getUser(@Param("userId") Integer user);

    Boolean existsByProductIdAndUserIdAndSize(Integer product_id, Integer user_id, String size);


    Cart findByProductIdAndUserIdAndSize(Integer product_id, Integer user_id, String size);

    @Query(value = "select c.* from cart c " +
            "join `user` u on c.user_id = u.id " +
            "join product p2 on p2.id = c.product_id " +
            "where u.id =:userId",
            nativeQuery = true)
    List<Cart> getCartByUserId(@Param("userId") String id);

    List<Cart> findAllByUser(User byId);

    @Transactional
    @Modifying
    @Query(value = "delete from cart where cart.user_id =:id", nativeQuery = true)
    void deleteCartByIdUser(@Param("id") Integer user);
}
