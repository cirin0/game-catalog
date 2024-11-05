package com.gamecatalog.gamecatalog.service;

import com.gamecatalog.gamecatalog.mapper.ReviewMapper;
import com.gamecatalog.gamecatalog.model.dto.ReviewDto;
import com.gamecatalog.gamecatalog.model.entity.Game;
import com.gamecatalog.gamecatalog.model.entity.Review;
import com.gamecatalog.gamecatalog.repository.GameRepository;
import com.gamecatalog.gamecatalog.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
  private final ReviewRepository reviewRepository;
  private final GameRepository gameRepository;
  private final ReviewMapper reviewMapper;

  public List<ReviewDto> getAllReviews() {
    List<Review> reviews = reviewRepository.findAll();
    return reviewMapper.toDtoList(reviews);
  }

  public ReviewDto getReviewById(Long id) {
    Review review = reviewRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Review not found"));
    return reviewMapper.toDto(review);
  }

  public List<ReviewDto> getReviewsByGameId(Long gameId) {
    List<Review> reviews = reviewRepository.findByGameId(gameId);
    return reviewMapper.toDtoList(reviews);
  }

  public ReviewDto createReview(Long gameId, ReviewDto reviewDto) {
    Game game = gameRepository.findById(gameId)
        .orElseThrow(() -> new RuntimeException("Game not found"));
    Review review = reviewMapper.toEntity(reviewDto);
    review.setGame(game);
    Review savedReview = reviewRepository.save(review);
    updateGameRating(game);
    return reviewMapper.toDto(savedReview);
  }

  public ReviewDto updateReview(Long id, ReviewDto reviewDto) {
    Review review = reviewRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Review not found"));
    Review updatedReview = reviewMapper.partialUpdate(reviewDto, review);
    Review savedReview = reviewRepository.save(updatedReview);
    updateGameRating(savedReview.getGame());
    return reviewMapper.toDto(savedReview);
  }

  public void deleteReview(Long id) {
    Review review = reviewRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Review not found"));
    Game game = review.getGame();
    reviewRepository.deleteById(id);
    updateGameRating(game);
  }

  private void updateGameRating(Game game) {
    Double averageRating = reviewRepository.calculateAverageRatingForGame(game.getId());
    game.setRating(averageRating != null ? averageRating : 0.0);
    gameRepository.save(game);
  }

}
