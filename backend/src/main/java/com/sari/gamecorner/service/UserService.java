package com.sari.gamecorner.service;

import com.sari.gamecorner.dto.UserDTO;
import com.sari.gamecorner.model.Game;
import com.sari.gamecorner.model.User;
import com.sari.gamecorner.repsitory.GameRepository;
import com.sari.gamecorner.repsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    public boolean handleRegistration(UserDTO userDTO) {
        if (!isEmailAlreadyExist(userDTO.getEmailAddress()) && !isUsernameAlreadyExist(userDTO.getUsername())) {
            User user = User.builder().name(userDTO.getUsername())
                    .emailAddress(userDTO.getEmailAddress())
                    .registrationDate(LocalDate.now())
                    .password(userDTO.getPassword()).build();
            userRepository.save(user);

            return true;
        }

        return false;
    }

    private boolean isEmailAlreadyExist(String email) {
        return userRepository.findUserByEmailAddress(email) != null;
    }

    private boolean isUsernameAlreadyExist(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public User login(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());

        if (user != null && user.getPassword().equals(userDTO.getPassword())) {
            return user;
        }

        return null;
    }

    public boolean toggleGameInLikedGames(String username, long id) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            Optional<Game> favouriteGame = gameRepository.findById(id);
            if (favouriteGame.isPresent() && user.getFavourite().contains(favouriteGame.get())) {
                user.removeGameFromFavouriteGames(favouriteGame.get());
            } else {
                user.addGameToFavouriteGames(favouriteGame.get());
            }
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean toggleGameInWalkthroughGames(String username, long id) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            Optional<Game> walkthroughGame = gameRepository.findById(id);
            if (walkthroughGame.isPresent() && user.getWalkthroughGames().contains(walkthroughGame.get())) {
                user.removeGameFromWalkthroughGame(walkthroughGame.get());
            } else {
                user.addGameToWalkthroughGames(walkthroughGame.get());
            }
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean toggleGameInGameList(String username, long id) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            Optional<Game> game = gameRepository.findById(id);
            if (game.isPresent() && user.getGameList().contains(game.get())) {
                user.removeGameFromGameList(game.get());
            } else {
                user.addGameToGameList(game.get());
            }
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }
}
