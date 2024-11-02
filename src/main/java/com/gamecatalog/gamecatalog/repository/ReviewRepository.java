package com.gamecatalog.gamecatalog.repository;

import com.gamecatalog.gamecatalog.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  @Query("SELECT AVG(r.rating) FROM Review r WHERE r.game.id = :gameId")
  Double calculateAverageRatingForGame(Long gameId);
}