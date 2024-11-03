package com.gamecatalog.gamecatalog.mapper;

import com.gamecatalog.gamecatalog.model.dto.ReviewDto;
import com.gamecatalog.gamecatalog.model.entity.Review;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {
  Review toEntity(ReviewDto reviewDto);

  ReviewDto toDto(Review review);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Review partialUpdate(ReviewDto reviewDto, @MappingTarget Review review);

  List<ReviewDto> toDtoList(List<Review> reviews);
}