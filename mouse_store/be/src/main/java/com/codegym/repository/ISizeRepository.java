package com.codegym.repository;

import com.codegym.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ISizeRepository extends JpaRepository<Size, Integer> {
    @Query(value = "select * from size", nativeQuery = true)
    Set<Size> getAllSize();

    @Query(value = "select s.* from `size` s where s.name = 'M'",nativeQuery = true)
    Optional<Size> sizeM();

    @Query(value = "select s.* from `size` s where s.name = 'L'",nativeQuery = true)
    Optional<Size> sizeL();

    @Query(value = "select s.* from `size` s where s.name = 'XL'",nativeQuery = true)
    Optional<Size> sizeXL();

    @Query(value = "select s.* from `size` s where s.name = 'S'", nativeQuery = true)
    Optional<Size> sizeS();

}
