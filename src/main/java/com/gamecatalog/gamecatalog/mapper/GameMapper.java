package com.gamecatalog.gamecatalog.mapper;

import com.gamecatalog.gamecatalog.model.dto.GameDto;
import com.gamecatalog.gamecatalog.model.entity.Game;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameMapper {
  Game toEntity(GameDto gameDto);

  @Mapping(target = "categoryId", source = "category.id")
  @Mapping(target = "categoryName", source = "category.name")
  GameDto toDto(Game game);

  List<GameDto> toDtoList(List<Game> games);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)Game
  partialUpdate(GameDto gameDto, @MappingTarget Game game);
}