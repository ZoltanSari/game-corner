package com.sari.gamecorner.controller;

import com.sari.gamecorner.dto.ErrorDTO;
import com.sari.gamecorner.dto.UserDTO;
import com.sari.gamecorner.model.User;
import com.sari.gamecorner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("registration")
    public ResponseEntity registration(@RequestBody UserDTO userDTO) {
        if (userService.handleRegistration(userDTO)) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorDTO.builder()
                        .error("invalid credentials")
                        .message("This username or email address is already exist")
                        .build());
    }

    @PostMapping("/login")
    public User login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{username}/liked-games/{gameId}")
    public ResponseEntity toggleGameInLikedGames(@PathVariable String username, @PathVariable long gameId) {
        if (userService.toggleGameInLikedGames(username, gameId)) return new ResponseEntity(HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorDTO.builder()
                        .error("invalid parameters")
                        .message("Username or game id is invalid in the URL")
                        .build());
    }

    @PutMapping("/{username}/walkthrough-games/{gameId}")
    public ResponseEntity toggleGameInWalkthroughGames(@PathVariable String username, @PathVariable long gameId) {
        if (userService.toggleGameInWalkthroughGames(username, gameId)) return new ResponseEntity(HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorDTO.builder()
                        .error("invalid parameters")
                        .message("Username or game id is invalid in the URL")
                        .build());
    }

    @PutMapping("/{username}/game-list/{gameId}")
    public ResponseEntity toggleGameInGameList(@PathVariable String username, @PathVariable long gameId) {
        if (userService.toggleGameInGameList(username, gameId)) return new ResponseEntity(HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorDTO.builder()
                        .error("invalid parameters")
                        .message("Username or game id is invalid in the URL")
                        .build());
    }
}
