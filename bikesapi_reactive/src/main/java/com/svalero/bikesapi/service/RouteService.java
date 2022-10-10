package com.svalero.bikesapi.service;

import com.svalero.bikesapi.domain.Bike;
import com.svalero.bikesapi.domain.Route;
import com.svalero.bikesapi.domain.dto.RouteDTO;
import com.svalero.bikesapi.exception.BikeNotFoundException;
import com.svalero.bikesapi.exception.UserNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteService {

    Flux<Route> findAllRoutes();

    Mono<Route> addRoute(RouteDTO routeDto) throws UserNotFoundException, BikeNotFoundException;
    Flux<Route> findRoutes(Bike bike, int distance);
    Flux<Route> findRoutes(Bike bike);
}
