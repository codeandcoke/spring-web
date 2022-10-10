package com.svalero.bikesapi.service;

import com.svalero.bikesapi.domain.Bike;
import com.svalero.bikesapi.exception.BikeNotFoundException;
import com.svalero.bikesapi.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BikeServiceImpl implements BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    @Override
    public Flux<Bike> findAllBikes() {
        return bikeRepository.findAll();
    }

    @Override
    public Flux<Bike> findAllBikes(int stationId) {
        return bikeRepository.findByStationId(stationId);
    }

    @Override
    public Mono<Bike> findBike(String id) throws BikeNotFoundException {
        return bikeRepository.findById(id).onErrorReturn(new Bike());
    }

    @Override
    public void repairBike(Bike bike) {

    }

    @Override
    public Mono<Bike> addBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    @Override
    public Mono<Bike> deleteBike(String id) throws BikeNotFoundException {
        Mono<Bike> bike = bikeRepository.findById(id).onErrorReturn(new Bike());
        bikeRepository.delete(bike.block());
        return bike;
    }

    @Override
    public Mono<Bike> modifyBike(String id, Bike newBike) throws BikeNotFoundException {
        Mono<Bike> monoBike = bikeRepository.findById(id).onErrorReturn(new Bike());

        Bike bike = monoBike.block();
        bike.setAvailable(newBike.isAvailable());
        bike.setBabyChair(newBike.isBabyChair());
        bike.setBattery(newBike.getBattery());
        bike.setKilometers(newBike.getKilometers());
        bike.setStationId(newBike.getStationId());

        return bikeRepository.save(bike);
    }
}
