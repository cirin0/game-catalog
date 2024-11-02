package com.gamecatalog.gamecatalog.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
  private Long id;
  private String content;
  private Integer rating;
  private Long gameId;
  private String gameName;
}
