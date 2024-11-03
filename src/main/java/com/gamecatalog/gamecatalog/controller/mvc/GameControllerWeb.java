package com.gamecatalog.gamecatalog.controller.mvc;

import com.gamecatalog.gamecatalog.model.dto.GameDto;
import com.gamecatalog.gamecatalog.model.dto.ReviewDto;
import com.gamecatalog.gamecatalog.service.CategoryService;
import com.gamecatalog.gamecatalog.service.GameService;
import com.gamecatalog.gamecatalog.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameControllerWeb {
  private final GameService gameService;
  private final CategoryService categoryService;
  private final ReviewService reviewService;

  @GetMapping
  public String index(Model model) {
    model.addAttribute("games", gameService.getAllGames());
    return "games/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable Long id, Model model) {
    try {
      GameDto game = gameService.getGameById(id);
      model.addAttribute("game", game);
      model.addAttribute("reviews", reviewService.getReviewsByGameId(id));
      return "games/show";
    } catch (RuntimeException e) {
      return "error/404";
    }
  }

  @GetMapping("/new")
  public String newGame(Model model) {
    model.addAttribute("game", new GameDto());
    model.addAttribute("categories", categoryService.getAllCategories());
    return "games/new";
  }

  @PostMapping
  public String create(@ModelAttribute GameDto gameDto, RedirectAttributes redirectAttributes) {
    try {
      GameDto created = gameService.createGame(gameDto);
      redirectAttributes.addFlashAttribute("successMessage", "Game created successfully");
      return "redirect:/games/" + created.getId();
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Failed to create game" + e.getMessage());
      return "redirect:/games/new";
    }
  }

  @GetMapping("/{id}/edit")
  public String edit(@PathVariable Long id, Model model) {
    try {
      model.addAttribute("game", gameService.getGameById(id));
      model.addAttribute("categories", categoryService.getAllCategories());
      return "games/edit";
    } catch (RuntimeException e) {
      return "error/404";
    }
  }

  @PostMapping("/{id}")
  public String update(@PathVariable Long id, @ModelAttribute GameDto gameDto, RedirectAttributes redirectAttributes) {
    try {
      gameService.updateGame(id, gameDto);
      redirectAttributes.addFlashAttribute("successMessage", "Game updated successfully");
      return "redirect:/games/" + id;
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Failed to update game" + e.getMessage());
      return "redirect:/games/" + id + "/edit";
    }
  }

  @PostMapping("/{id}/delete")
  public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
      gameService.deleteGame(id);
      redirectAttributes.addFlashAttribute("successMessage", "Game deleted successfully");
      return "redirect:/games";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete game" + e.getMessage());
      return "redirect:/games/" + id;
    }
  }

  @PostMapping("/{id}/reviews")
  public String createReview(@PathVariable Long id, @ModelAttribute ReviewDto reviewDto, RedirectAttributes redirectAttributes) {
    try {
      reviewService.createReview(id, reviewDto);
      redirectAttributes.addFlashAttribute("successMessage", "Review created successfully");
      return "redirect:/games/" + id;
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Failed to create review" + e.getMessage());
      return "redirect:/games/" + id;
    }
  }
}
