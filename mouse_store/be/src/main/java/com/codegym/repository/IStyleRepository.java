package com.codegym.repository;

import com.codegym.model.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStyleRepository extends JpaRepository<Style, Integer> {
    @Query(value = "select * from `style`",nativeQuery = true)
    List<Style> getAllStyle();
}
