package com.codegym.repository;

import com.codegym.model.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOriginRepository extends JpaRepository<Origin, Integer> {
    @Query(value = "select * from origin", nativeQuery = true)
    List<Origin> getAllOrigin();
}
