package com.gamecatalog.gamecatalog.repository;

import com.gamecatalog.gamecatalog.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  @Query("SELECT AVG(r.rating) FROM Review r WHERE r.game.id = :gameId")
  Double calculateAverageRatingForGame(Long gameId);

  List<Review> findByGameId(Long gameId);
}