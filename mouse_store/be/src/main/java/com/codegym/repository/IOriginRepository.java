package com.codegym.repository;

import com.codegym.model.Origin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOriginRepository extends JpaRepository<Origin, Integer> {
}
