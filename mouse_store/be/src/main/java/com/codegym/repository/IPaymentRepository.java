package com.codegym.repository;

import com.codegym.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPaymentRepository extends JpaRepository<Payment, Integer> {

    @Query(value = "select p.* from payment p " +
            "join bill b on p.id = b.payment_id " +
            "join `user` u on u.id = b.user_id " +
            "where b.user_id =:id and p.payment_status = false ",nativeQuery = true)
    Payment getPaymentById(@Param("id") Integer id);
}
