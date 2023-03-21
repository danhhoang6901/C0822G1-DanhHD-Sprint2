package com.codegym.repository;

import com.codegym.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWareHouseRepository extends JpaRepository<Warehouse, Integer> {
}
