package com.gamecatalog.gamecatalog.controller.api;

import com.gamecatalog.gamecatalog.model.dto.CategoryDto;
import com.gamecatalog.gamecatalog.model.dto.GameDto;
import com.gamecatalog.gamecatalog.service.CategoryService;
import com.gamecatalog.gamecatalog.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;
  private final GameService gameService;

  @GetMapping
  public ResponseEntity<List<CategoryDto>> getAllCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }

  @GetMapping("{id}")
  public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
    return ResponseEntity.ok(categoryService.getCategoryById(id));
  }

  @GetMapping("{id}/games")
  public ResponseEntity<List<GameDto>> getGamesByCategory(@PathVariable Long id) {
    return ResponseEntity.ok(gameService.getGamesByCategory(id));
  }

  @PostMapping
  public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
    CategoryDto createdCategory = categoryService.createCategory(categoryDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
  }

  @PutMapping("{id}")
  public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,
                                            @RequestBody CategoryDto categoryDto) {
    return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.noContent().build();
  }
}
