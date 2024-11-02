package com.gamecatalog.gamecatalog.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDto {
  private Long id;
  private Long gameId;
  private String gameName;
}