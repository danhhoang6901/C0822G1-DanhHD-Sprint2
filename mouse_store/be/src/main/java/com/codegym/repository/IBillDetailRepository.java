package com.codegym.repository;

import com.codegym.dto.IBillDetailDto;
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
            "join product p2 on p2.id = bd.product_id " +
            "where u.id =:userId",
            nativeQuery = true)
    List<BillDetail> getCartByUserId(@Param("userId") String id);


    @Query(value = "select bd.id as id, bd.size as `size`, bd.quantity as quantity, b.date_purchase as datePurchase, p.price as price,p.name as name\n" +
            "from `bill_detail` bd\n" +
            "    join `product` p on p.id = bd.product_id\n" +
            "    join `bill` b on b.id = bd.bill_id\n" +
            "    join `user` u on u.id = b.user_id where b.id =:id", nativeQuery = true)
    List<IBillDetailDto> showBillDetail(@Param("id") Integer id);
}
