package com.gamecatalog.gamecatalog.service;

import com.gamecatalog.gamecatalog.mapper.GameMapper;
import com.gamecatalog.gamecatalog.model.dto.CategoryDto;
import com.gamecatalog.gamecatalog.model.dto.GameDto;
import com.gamecatalog.gamecatalog.model.entity.Category;
import com.gamecatalog.gamecatalog.model.entity.Game;
import com.gamecatalog.gamecatalog.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
  private final GameRepository gameRepository;
  private final GameMapper gameMapper;
  private final CategoryService categoryService;

  public List<GameDto> getAllGames() {
    List<Game> games = gameRepository.findAll();
    return gameMapper.toDtoList(games);
  }

  public GameDto getGameById(Long id) {
    Game game = gameRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Game not found"));
    return gameMapper.toDto(game);
  }

  public List<GameDto> getGamesByCategory(Long id) {
    List<Game> games = gameRepository.findByCategoryId(id);
    return gameMapper.toDtoList(games);
  }

  public GameDto createGame(GameDto gameDto) {
    CategoryDto category = categoryService.getCategoryById(gameDto.getCategoryId());
    Game game = gameMapper.toEntity(gameDto);
    game.setCategory(Category.builder()
        .id(category.getId())
        .name(category.getName())
        .description(category.getDescription())
        .build());
    Game savedGame = gameRepository.save(game);
    return gameMapper.toDto(savedGame);
  }

  public GameDto updateGame(Long id, GameDto gameDto) {
    Game game = gameRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Game not found"));
    CategoryDto category = categoryService.getCategoryById(gameDto.getCategoryId());
    Game updatedGame = gameMapper.partialUpdate(gameDto, game);
    game.setCategory(Category.builder()
        .id(category.getId())
        .name(category.getName())
        .description(category.getDescription())
        .build());
    return gameMapper.toDto(gameRepository.save(updatedGame));
  }

  public void deleteGame(Long id) {
    gameRepository.deleteById(id);
  }
}
