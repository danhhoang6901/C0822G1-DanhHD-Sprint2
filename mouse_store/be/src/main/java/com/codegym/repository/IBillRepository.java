package com.codegym.repository;

import com.codegym.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBillRepository extends JpaRepository<Bill, Integer> {
    @Query(value = "select b.* from bill b join `user` u on u.id = b.user_id where b.user_id =:id", nativeQuery = true)
    Page<Bill> showBill(Pageable pageable, @Param("id") Integer id);

    @Query(value = "select b.* from bill b " +
            "join `user` u on u.id = b.user_id " +
            "where b.user_id =:userId",
            nativeQuery = true)
    Bill getBill(@Param("userId") Integer user);


    @Query(value = "select * from bill", nativeQuery = true)
    List<Bill> showAllBill();
}
