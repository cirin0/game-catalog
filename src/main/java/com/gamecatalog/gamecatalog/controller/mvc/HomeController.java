package com.gamecatalog.gamecatalog.controller.mvc;

import com.gamecatalog.gamecatalog.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
  private final GameService gameService;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("games", gameService.getAllGames());
    return "home/index";
  }
}
