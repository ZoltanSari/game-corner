package com.sari.gamecorner.service;

import com.sari.gamecorner.dto.UserDTO;
import com.sari.gamecorner.model.Game;
import com.sari.gamecorner.model.User;
import com.sari.gamecorner.repsitory.GameRepository;
import com.sari.gamecorner.repsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    public void registration(UserDTO userDTO) {
        User user = User.builder().name(userDTO.getName())
                .emailAddress(userDTO.getEmailAddress())
                .password(userDTO.getPassword()).build();

        userRepository.save(user);
    }

    public User login(UserDTO userDTO) {
        User user = userRepository.findByName(userDTO.getName());

        if (user != null && user.getPassword().equals(userDTO.getPassword())) {
            return user;
        }

        return null;
    }

    public void likeGame(String username, long id) {
        User user = userRepository.findByName(username);

        if (user != null) {
            Optional<Game> favouriteGame = gameRepository.findById(id);
            if (favouriteGame.isPresent() && user.getFavourite().contains(favouriteGame.get())) {
                user.removeGameFromFavouriteGames(favouriteGame.get());
            } else {
                user.addGameToFavouriteGames(favouriteGame.get());
            }
        }

        userRepository.save(user);
    }

    public void walkthroughGames(String username, long id) {
        User user = userRepository.findByName(username);

        if (user != null) {
            Optional<Game> walkthroughGame = gameRepository.findById(id);
            if (walkthroughGame.isPresent() && user.getWalkthroughGames().contains(walkthroughGame.get())) {
                user.removeGameFromWalkthroughGame(walkthroughGame.get());
            } else {
                user.addGameToWalkthroughGames(walkthroughGame.get());
            }
        }

        userRepository.save(user);
    }

    public void gameList(String username, long id) {
        User user = userRepository.findByName(username);

        if (user != null) {
            Optional<Game> game = gameRepository.findById(id);
            if (game.isPresent() && user.getGameList().contains(game.get())) {
                user.removeGameFromGameList(game.get());
            } else {
                user.addGameToGameList(game.get());
            }
        }

        userRepository.save(user);
    }
}
