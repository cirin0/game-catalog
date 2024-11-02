package com.gamecatalog.gamecatalog.mapper;

import com.gamecatalog.gamecatalog.model.dto.CategoryDto;
import com.gamecatalog.gamecatalog.model.entity.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
  Category toEntity(CategoryDto categoryDto);

  CategoryDto toDto(Category category);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Category partialUpdate(CategoryDto categoryDto, @MappingTarget Category category);

  List<CategoryDto> toDtoList(List<Category> categories);
}