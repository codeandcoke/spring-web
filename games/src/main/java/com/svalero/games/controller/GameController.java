package com.svalero.games.controller;

import com.svalero.games.domain.Game;
import com.svalero.games.exception.GameNotFoundException;
import com.svalero.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public ResponseEntity<List<Game>> getGames() {
        // TODO Analizar la petición
        List<Game> games = gameService.getGames();
        // TODO Preparar la respuesta
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("/games/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable long gameId) throws GameNotFoundException {
        Game game = gameService.getGame(gameId);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    public void addGame(Game game) {

    }

    public void deleteGame(Game game) {

    }

    public void updateGame(Game game) {

    }

    public void rateGame(Game game, int rate) {

    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<String> handleGameNotFoundException(GameNotFoundException gameNotFoundException) {
        return new ResponseEntity<>("asdasd", HttpStatus.NOT_FOUND);
    }
}
