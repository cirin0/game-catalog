package com.gamecatalog.gamecatalog.mapper;

import com.gamecatalog.gamecatalog.model.dto.WishlistDto;
import com.gamecatalog.gamecatalog.model.entity.Wishlist;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface WishlistMapper {
  Wishlist toEntity(WishlistDto wishlistDto);

  @Mapping(target = "gameId", source = "game.id")
  @Mapping(target = "gameName", source = "game.name")
  WishlistDto toDto(Wishlist wishlist);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)Wishlist
  partialUpdate(WishlistDto wishlistDto, @MappingTarget Wishlist wishlist);
}