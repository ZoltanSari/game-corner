package com.sari.gamecorner.service;

import com.sari.gamecorner.model.Game;
import com.sari.gamecorner.repsitory.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public void addGame(Game game) {
        gameRepository.save(game);
    }
}
