package com.gamecatalog.gamecatalog.service;

import com.gamecatalog.gamecatalog.mapper.GameMapper;
import com.gamecatalog.gamecatalog.model.dto.GameDto;
import com.gamecatalog.gamecatalog.model.entity.Game;
import com.gamecatalog.gamecatalog.model.entity.Wishlist;
import com.gamecatalog.gamecatalog.repository.GameRepository;
import com.gamecatalog.gamecatalog.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistService {
  private final WishlistRepository wishlistRepository;
  private final GameRepository gameRepository;
  private final GameMapper gameMapper;

  public List<GameDto> getWishList(){
    return wishlistRepository.findAll().stream()
        .map(wishlist -> gameMapper.toDto(wishlist.getGame()))
        .collect(Collectors.toList());
  }

  public void addGameToWishlist(Long gameId) {
    if (wishlistRepository.existsByGameId(gameId)) {
      throw new RuntimeException("Game already in wishlist");
    }
    Game game = gameRepository.findById(gameId)
        .orElseThrow(() -> new RuntimeException("Game not found"));

    Wishlist wishlistItem = Wishlist.builder()
        .game(game)
        .build();

    wishlistRepository.save(wishlistItem);
  }

  public void removeGameFromWishlist(Long gameId) {
    Wishlist wishlistItem = wishlistRepository.findByGameId(gameId);
    wishlistRepository.delete(wishlistItem);
  }

}
