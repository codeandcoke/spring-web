package com.svalero.games.repository;

import com.svalero.games.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    List<Game> findAll();
    Game findByTitle(String title);
    List<Game> findByPublisher(String publisher);
    List<Game> findByPublisherAndPrice(String publisher, float price);
}
