package com.codegym.repository;

import com.codegym.model.Product;
import com.codegym.model.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface IProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product where name like concat('%', :name ,'%') and flag_delete = false order by id desc", nativeQuery = true)
    List<Product> getAllProduct(@Param("name") String name);

    @Query(value = "select * from product where id =:id and flag_delete = false ", nativeQuery = true)
    Product findProductById(@Param("id") Integer id);

    @Query(value = "select * from product where name like concat('%', :search ,'%') and flag_delete = false ", nativeQuery = true)
    Page<Product> showListProduct(@Param("search") String search, Pageable pageable);

    @Modifying
    @Query(value = "insert into product" +
            "(code_product, " +
            "`name`, " +
            "image, " +
            "color, " +
            "description, " +
            "quantity, " +
            "price, " +
            "material, " +
            "washing_instructions, " +
            "flag_delete, " +
            "style_id, " +
            "origin_id, " +
            "trademark_id, " +
            "size_id, " +
            "category_id) " +
            "values " +
            "(:#{#product.codeProduct}, " +
            ":#{#product.name}, " +
            ":#{#product.image}, " +
            ":#{#product.color}, " +
            ":#{#product.description}, " +
            ":#{#product.quantity}, " +
            ":#{#product.price}, " +
            ":#{#product.material}, " +
            ":#{#product.washingInstructions}, " +
            ":#{#product.flagDelete}, " +
            ":#{#product.style.id}, " +
            ":#{#product.origin.id}, " +
            ":#{#product.trademark.id}, " +
            ":#{#product.sizes}, " +
            ":#{#product.category.id})",nativeQuery = true)
    void createProduct(@Param("product") Product product);
}
