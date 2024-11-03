package com.gamecatalog.gamecatalog.controller.mvc;

import com.gamecatalog.gamecatalog.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistControllerWeb {
  private final WishlistService wishlistService;

  @GetMapping
  public String index(Model model) {
    model.addAttribute("wishlist", wishlistService.getWishList());
    return "wishlist/index";
  }

  @PostMapping("/add/{gameId}")
  public String addGameToWishlist(@PathVariable Long gameId, RedirectAttributes redirectAttributes) {
    try {
      wishlistService.addGameToWishlist(gameId);
      redirectAttributes.addFlashAttribute("successMessage", "Game added to wishlist");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Failed to add game to wishlist");
    }
    return "redirect:/wishlist";
  }

  @PostMapping("/remove/{gameId}")
  public String removeGameFromWishlist(@PathVariable Long gameId, RedirectAttributes redirectAttributes) {
    try {
      wishlistService.removeGameFromWishlist(gameId);
      redirectAttributes.addFlashAttribute("successMessage", "Game removed from wishlist");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Failed to remove game from wishlist");
    }
    return "redirect:/wishlist";
  }
}
