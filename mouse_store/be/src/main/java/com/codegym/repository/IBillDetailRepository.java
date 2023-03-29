package com.codegym.repository;

import com.codegym.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface IBillDetailRepository extends JpaRepository<BillDetail, Integer> {

    @Query(value = "select bd.* from bill_detail bd " +
            "join bill b on b.id = bd.bill_id " +
            "join `user` u on b.user_id = u.id " +
            "join payment p on p.id = b.payment_id " +
            "join product p2 on p2.id = bd.product_id " +
            "where p.payment_status = false and u.id =:userId",
            nativeQuery = true)
    List<BillDetail> getCartByUserId(@Param("userId") String id);
}
