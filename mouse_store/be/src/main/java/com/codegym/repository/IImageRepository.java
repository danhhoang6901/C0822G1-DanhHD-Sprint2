package com.codegym.repository;

import com.codegym.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IImageRepository extends JpaRepository<Image, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into image (url,product_id) values (:#{#image.url}, :#{#image.product})", nativeQuery = true)
    void createImage(@Param("image") Image image);
}
