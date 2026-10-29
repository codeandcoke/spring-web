package com.svalero.games.controller;

import com.svalero.games.domain.Game;
import com.svalero.games.exception.ErrorDTO;
import com.svalero.games.exception.GameExistsAlreadyException;
import com.svalero.games.exception.GameNotFoundException;
import com.svalero.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/games")
    public ResponseEntity<Game> addGame(@RequestBody Game game) throws GameExistsAlreadyException {
        Game newGame = gameService.addGame(game);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }

    @DeleteMapping("/games/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable long gameId) throws GameNotFoundException {
        gameService.deleteGame(gameId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/games/{gameId}")
    public ResponseEntity<Game> updateGame(@PathVariable long gameId, @RequestBody Game game)
            throws GameNotFoundException, GameExistsAlreadyException {
        Game updatedGame = gameService.updateGame(gameId, game);
        return new ResponseEntity<>(updatedGame, HttpStatus.OK);
    }

    public void rateGame(Game game, int rate) {

    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleGameNotFoundException(GameNotFoundException gameNotFoundException) {
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setErrorCode(404);
        errorDto.setErrorMessage(gameNotFoundException.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GameExistsAlreadyException.class)
    public ResponseEntity<ErrorDTO> handleGameExistsAlreadyException(GameExistsAlreadyException gameExistsAlreadyException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode(409);
        errorDTO.setErrorMessage(gameExistsAlreadyException.getMessage());

        return new ResponseEntity<>(errorDTO, HttpStatus.CONFLICT);
    }
}
