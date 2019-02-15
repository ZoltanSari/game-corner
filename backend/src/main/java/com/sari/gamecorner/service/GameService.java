package com.sari.gamecorner.service;

import com.sari.gamecorner.model.Game;
import com.sari.gamecorner.repsitory.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public void addGame(Game game) {
        gameRepository.save(game);
    }

    public List<Game> getAllGame() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElse(null);
    }

    public List<Game> getGameByTitle(String substring) {
        return gameRepository.findByNameContainingIgnoreCase(substring);
    }

    public List<Game> getTheMostPopularGames() {
        return gameRepository.findTop5ByOrderByRatingDesc();
    }
}
