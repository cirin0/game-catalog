package com.gamecatalog.gamecatalog.repository;

import com.gamecatalog.gamecatalog.model.entity.Game;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
  List<Game> findByCategoryId(Long categoryId);
}