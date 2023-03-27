package com.codegym.repository;

import com.codegym.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISizeRepository extends JpaRepository<Size, Integer> {
    @Query(value = "select * from size", nativeQuery = true)
    List<Size> getAllSize();
}
