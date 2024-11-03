package com.gamecatalog.gamecatalog.controller.mvc;

import com.gamecatalog.gamecatalog.model.dto.CategoryDto;
import com.gamecatalog.gamecatalog.service.CategoryService;
import com.gamecatalog.gamecatalog.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryControllerWeb {
  private final CategoryService categoryService;
  private final GameService gameService;

  @GetMapping
  public String index(Model model) {
    model.addAttribute("categories", categoryService.getAllCategories());
    return "categories/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable Long id, Model model) {
    try {
      model.addAttribute("category", categoryService.getCategoryById(id));
      model.addAttribute("games", gameService.getGamesByCategory(id));
      return "categories/show";
    } catch (RuntimeException e) {
      return "error/404";
    }
  }

  @GetMapping("/new")
  public String newCategory(Model model) {
    model.addAttribute("category", new CategoryDto());
    return "categories/new";
  }

  @PostMapping
  public String create(@ModelAttribute CategoryDto categoryDto, RedirectAttributes redirectAttributes) {
    try {
      CategoryDto created = categoryService.createCategory(categoryDto);
      redirectAttributes.addFlashAttribute("successMessage", "Category created successfully");
      return "redirect:/categories/" + created.getId();
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Failed to create category" + e.getMessage());
      return "redirect:/categories/new";
    }
  }

  @GetMapping("/{id}/edit")
  public String edit(@PathVariable Long id, Model model) {
    try {
      model.addAttribute("category", categoryService.getCategoryById(id));
      return "categories/edit";
    } catch (RuntimeException e) {
      return "error/404";
    }
  }

  @PostMapping("/{id}")
  public String update(@PathVariable Long id, @ModelAttribute CategoryDto categoryDto, RedirectAttributes redirectAttributes) {
    try {
      categoryService.updateCategory(id, categoryDto);
      redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully");
      return "redirect:/categories/" + id;
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Failed to update category" + e.getMessage());
      return "redirect:/categories/" + id + "/edit";
    }
  }

  @PostMapping("/{id}/delete")
  public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
      categoryService.deleteCategory(id);
      redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully");
      return "redirect:/categories";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete category" + e.getMessage());
      return "redirect:/categories/" + id;
    }
  }
}
