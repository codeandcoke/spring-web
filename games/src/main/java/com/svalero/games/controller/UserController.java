package com.svalero.games.controller;

import com.svalero.games.domain.Game;
import com.svalero.games.domain.User;
import com.svalero.games.exception.ErrorDTO;
import com.svalero.games.exception.UserNotFoundException;
import com.svalero.games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> post(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/games")
    public ResponseEntity<List<Game>> getGames(@PathVariable long userId) throws UserNotFoundException {
        List<Game> games = userService.getGames(userId);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }


//    @PostMapping("/login")
//    public ResponseEntity<Void> login(@RequestBody UserDto userDto) {
//
//    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleGameNotFoundException(UserNotFoundException userNotFoundException) {
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setErrorCode(404);
        errorDto.setErrorMessage(userNotFoundException.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
