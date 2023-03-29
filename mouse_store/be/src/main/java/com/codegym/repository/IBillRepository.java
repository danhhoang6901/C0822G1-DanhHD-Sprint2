package com.codegym.repository;

import com.codegym.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBillRepository extends JpaRepository<Bill, Integer> {

    @Query(value = "select b.* from bill b " +
            "join payment p on p.id = b.payment_id " +
            "join `user` u on u.id = b.user_id " +
            "where b.user_id =:userId and p.payment_status = false",
            nativeQuery = true)
    Bill getBill(@Param("userId") Integer user);
}
