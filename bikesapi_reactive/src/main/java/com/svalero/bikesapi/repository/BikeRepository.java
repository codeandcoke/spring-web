package com.svalero.bikesapi.repository;

import com.svalero.bikesapi.domain.Bike;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BikeRepository extends ReactiveMongoRepository<Bike, String> {

    Flux<Bike> findAll();
    Flux<Bike> findByStationId(int stationId);
    Flux<Bike> findByBabyChair(boolean babyChair);
    Flux<Bike> findByBatteryGreaterThan(int battery);
}
