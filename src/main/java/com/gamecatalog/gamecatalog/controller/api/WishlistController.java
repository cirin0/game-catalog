package com.gamecatalog.gamecatalog.controller.api;

import com.gamecatalog.gamecatalog.model.dto.GameDto;
import com.gamecatalog.gamecatalog.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
@RequiredArgsConstructor
public class WishlistController {
  private final WishlistService wishlistService;

  @GetMapping
  public ResponseEntity<List<GameDto>> getWishlist() {
    return ResponseEntity.ok(wishlistService.getWishList());
  }

  @PostMapping("/games/{gameId}")
  public ResponseEntity<Void> addGameToWishlist(@PathVariable Long gameId) {
    wishlistService.addGameToWishlist(gameId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/games/{gameId}")
  public ResponseEntity<Void> removeGameFromWishlist(@PathVariable Long gameId) {
    wishlistService.removeGameFromWishlist(gameId);
    return ResponseEntity.noContent().build();
  }
}
