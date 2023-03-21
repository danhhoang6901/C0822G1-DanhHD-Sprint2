package com.codegym.repository;

import com.codegym.model.BillHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBillHistoryRepository extends JpaRepository<BillHistory, Integer> {
}
