package com.codegym.repository;

import com.codegym.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IImageRepository extends JpaRepository<Image, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into image (url,product_id) values (:#{#image.url}, :#{#image.product})", nativeQuery = true)
    void createImage(@Param("image") Image image);

    @Query(value = "select * from image where product_id =:id", nativeQuery = true)
    List<Image> showImageByIdProduct(@Param("id") Integer id);
}
