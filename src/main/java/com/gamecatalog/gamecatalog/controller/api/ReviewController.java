package com.gamecatalog.gamecatalog.controller.api;

import com.gamecatalog.gamecatalog.model.dto.ReviewDto;
import com.gamecatalog.gamecatalog.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
  private final ReviewService reviewService;

  @GetMapping
  public ResponseEntity<List<ReviewDto>> getAllReviews() {
    return ResponseEntity.ok(reviewService.getAllReviews());
  }

  @GetMapping("/games/{gameId}")
  public ResponseEntity<List<ReviewDto>> getReviewsByGameId(@PathVariable Long gameId) {
    return ResponseEntity.ok(reviewService.getReviewsByGameId(gameId));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
    Optional<ReviewDto> review = Optional.ofNullable(reviewService.getReviewById(id));
    return review.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @PostMapping("/games/{gameId}")
  public ResponseEntity<ReviewDto> createReview(@PathVariable Long gameId,
                                                @RequestBody ReviewDto reviewDto) {
    ReviewDto createdReview = reviewService.createReview(gameId, reviewDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ReviewDto> updateReview(@PathVariable Long id,
                                                @RequestBody ReviewDto reviewDto) {
    Optional<ReviewDto> updatedReview = Optional.ofNullable(reviewService.updateReview(id, reviewDto));
    return updatedReview.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
    reviewService.deleteReview(id);
    return ResponseEntity.noContent().build();
  }
}
