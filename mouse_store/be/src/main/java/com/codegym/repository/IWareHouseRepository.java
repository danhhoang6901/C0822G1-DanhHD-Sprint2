package com.codegym.repository;

import com.codegym.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IWareHouseRepository extends JpaRepository<Warehouse, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update product, `size` " +
            "set product.quantity = (product.quantity + :quantityNew), " +
            "`size`.quantity = (`size`.quantity + :quantityNew) " +
            "where product.id =:id " +
            "and `size`.id =:sizeId", nativeQuery = true)
    void wareHousing(@Param("id") Integer id, @Param("sizeId") Integer sizeId, @Param("quantityNew") int quantityNew);
}
