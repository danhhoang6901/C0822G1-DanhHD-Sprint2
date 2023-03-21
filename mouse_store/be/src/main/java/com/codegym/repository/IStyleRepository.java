package com.codegym.repository;

import com.codegym.model.Style;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStyleRepository extends JpaRepository<Style, Integer> {
}
