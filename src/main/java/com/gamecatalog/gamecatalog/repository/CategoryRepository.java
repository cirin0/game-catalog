package com.gamecatalog.gamecatalog.repository;

import com.gamecatalog.gamecatalog.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}