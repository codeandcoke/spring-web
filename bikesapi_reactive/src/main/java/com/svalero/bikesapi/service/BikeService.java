package com.svalero.bikesapi.service;

import com.svalero.bikesapi.domain.Bike;
import com.svalero.bikesapi.exception.BikeNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BikeService {

    Flux<Bike> findAllBikes();
    Flux<Bike> findAllBikes(int stationId);
    Mono<Bike> findBike(String id) throws BikeNotFoundException;
    void repairBike(Bike bike);

    Mono<Bike> addBike(Bike bike);
    Mono<Bike> deleteBike(String id) throws BikeNotFoundException;
    Mono<Bike> modifyBike(String id, Bike bike) throws BikeNotFoundException;
}
