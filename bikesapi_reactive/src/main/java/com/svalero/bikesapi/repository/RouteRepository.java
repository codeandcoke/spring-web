package com.svalero.bikesapi.repository;

import com.svalero.bikesapi.domain.Bike;
import com.svalero.bikesapi.domain.Route;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RouteRepository extends ReactiveMongoRepository<Route, String> {

    Flux<Route> findAll();
    Flux<Route> findByBike(Bike bike);
    Flux<Route> findByBikeAndKilometers(Bike bike, int kilometers);
}
