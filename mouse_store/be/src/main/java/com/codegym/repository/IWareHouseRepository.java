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
    @Query(value = "update product " +
            "set quantity = (quantity + :quantityNew) " +
            "where id =:id " , nativeQuery = true)
    void wareHousing(@Param("id") Integer id,  @Param("quantityNew") int quantityNew);
}
