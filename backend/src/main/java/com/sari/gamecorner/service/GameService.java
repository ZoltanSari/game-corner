package com.sari.gamecorner.service;

import com.sari.gamecorner.model.Game;
import com.sari.gamecorner.repsitory.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Game> addGamesToTheHomePage() {
        List<Game> homePageGames =gameRepository.findByNameContainingIgnoreCase("assassin");
        List<Game> formatCover = new ArrayList<>();

        for (Game game : homePageGames) {
            game.setCoverUrl(game.getCoverUrl().replace("t_thumb", "t_720p"));
            formatCover.add(game);
        }
        return formatCover;
    }
}
