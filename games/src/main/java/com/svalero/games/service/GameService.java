package com.svalero.games.service;

import com.svalero.games.domain.Game;
import com.svalero.games.exception.GameExistsAlreadyException;
import com.svalero.games.exception.GameNotFoundException;
import com.svalero.games.repository.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Game> getGames() {
        List<Game> games = gameRepository.findAll();
        // TODO Añadir la lógica necesaria
        return games;
    }

    public Game getGame(long gameId) throws GameNotFoundException {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(GameNotFoundException::new);
        return game;
    }

    public Game addGame(Game game) throws GameExistsAlreadyException {
        Game oldGame = gameRepository.findByTitle(game.getTitle());
        if (oldGame != null) {
            throw new GameExistsAlreadyException();
        }

        game.setRegisteredDate(LocalDate.now());
        Game newGame = gameRepository.save(game);
        return newGame;
    }

    public void deleteGame(long gameId) throws GameNotFoundException {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(GameNotFoundException::new);
        gameRepository.delete(game);
    }

    public Game updateGame(long gameId, Game game) throws GameNotFoundException, GameExistsAlreadyException {
        Game oldGame = gameRepository.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

        Game newGame = gameRepository.findByTitle(game.getTitle());
        if (newGame != null) {
            throw new GameExistsAlreadyException("The new title exists already");
        }

        modelMapper.map(game, oldGame);
        Game updatedGame = gameRepository.save(oldGame);
        return updatedGame;
    }
}
