package com.gamecatalog.gamecatalog.service;

import com.gamecatalog.gamecatalog.mapper.CategoryMapper;
import com.gamecatalog.gamecatalog.model.dto.CategoryDto;
import com.gamecatalog.gamecatalog.model.entity.Category;
import com.gamecatalog.gamecatalog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  public List<CategoryDto> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();
    return categoryMapper.toDtoList(categories);
  }

  public CategoryDto getCategoryById(Long id) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found"));
    return categoryMapper.toDto(category);
  }

  public CategoryDto createCategory(CategoryDto categoryDto) {
    Category category = categoryMapper.toEntity(categoryDto);
    Category savedCategory = categoryRepository.save(category);
    return categoryMapper.toDto(savedCategory);
  }

  public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found"));
    Category updatedCategory = categoryMapper.partialUpdate(categoryDto, category);
    return categoryMapper.toDto(categoryRepository.save(updatedCategory));
  }

  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }
}
