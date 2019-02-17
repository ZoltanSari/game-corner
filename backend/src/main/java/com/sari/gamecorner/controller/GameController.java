package com.sari.gamecorner.controller;

import com.sari.gamecorner.dto.ErrorDTO;
import com.sari.gamecorner.model.Game;
import com.sari.gamecorner.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getAllGame() {
        return gameService.getAllGame();
    }

    @GetMapping("/{gameId}")
    public ResponseEntity getSingleGame(@PathVariable Long gameId) {
        Game game = gameService.getGameById(gameId);

        if (game != null) return ResponseEntity.status(HttpStatus.OK).body(game);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDTO.builder()
                        .error("Game cannot be found")
                        .message("Game' id in the URL is incorrect")
                        .build());
    }


    @GetMapping("/search")
    public ResponseEntity getGameByTitle(@RequestParam String substring) {
        List<Game> games = gameService.getGameByTitle(substring);

        if (!games.isEmpty()) return ResponseEntity.status(HttpStatus.OK).body(games);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDTO.builder()
                        .error("Game not found")
                        .message("This game cannot be find in the database"));
    }

    @GetMapping("/top-5")
    public List<Game> getTheTop5Games() {
        return gameService.getTheMostPopularGames();
    }
}
