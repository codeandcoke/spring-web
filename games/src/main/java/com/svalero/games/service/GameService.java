package com.svalero.games.service;

import com.svalero.games.domain.Game;
import com.svalero.games.exception.GameNotFoundException;
import com.svalero.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

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
}
