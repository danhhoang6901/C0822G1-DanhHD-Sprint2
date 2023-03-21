package com.codegym.repository;

import com.codegym.model.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITrademarkRepository extends JpaRepository<Trademark, Integer> {
}
