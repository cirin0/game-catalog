package com.gamecatalog.gamecatalog.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
  private Long id;
  private String content;
  private Integer rating;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Long gameId;
  private String gameName;
}
