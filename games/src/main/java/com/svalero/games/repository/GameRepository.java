package com.svalero.games.repository;

import com.svalero.games.domain.Game;
import com.svalero.games.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    List<Game> findAll();
    Game findByTitle(String title);
    List<Game> findByPublisher(String publisher);
    List<Game> findByPublisherAndPrice(String publisher, float price);

    @Query(value = "SELECT * FROM games", nativeQuery = true)
    List<Game> getVentasDelAñoPorCiudad();
}
