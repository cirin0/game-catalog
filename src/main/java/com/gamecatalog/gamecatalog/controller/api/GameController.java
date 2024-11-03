package com.gamecatalog.gamecatalog.controller.api;

import com.gamecatalog.gamecatalog.model.dto.GameDto;
import com.gamecatalog.gamecatalog.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/games")
@RequiredArgsConstructor
public class GameController {
  private final GameService gameService;

  @GetMapping
  public ResponseEntity<List<GameDto>> getAllGames() {
    return ResponseEntity.ok(gameService.getAllGames());
  }

  @GetMapping("{id}")
  public ResponseEntity<GameDto> getGameById(@PathVariable Long id) {
    return ResponseEntity.ok(gameService.getGameById(id));
  }

  @PostMapping
  public ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto) {
    GameDto createdGame = gameService.createGame(gameDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
  }

  @PutMapping("{id}")
  public ResponseEntity<GameDto> updateGame(@PathVariable Long id,
                                            @RequestBody GameDto gameDto) {
    return ResponseEntity.ok(gameService.updateGame(id, gameDto));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
    gameService.deleteGame(id);
    return ResponseEntity.noContent().build();
  }
}
