package com.gamecatalog.gamecatalog.repository;

import com.gamecatalog.gamecatalog.model.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
  boolean existsByGameId(Long gameId);
  Wishlist findByGameId(Long gameId);
}