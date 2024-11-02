package com.gamecatalog.gamecatalog.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
  private Long id;
  private String name;
  private String description;
  private Integer releaseYear;
  private String developer;
  private String imageUrl;
  private Double rating;
  private Long categoryId;
  private String categoryName;
}
