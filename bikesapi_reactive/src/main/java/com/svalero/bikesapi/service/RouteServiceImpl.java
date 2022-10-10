package com.svalero.bikesapi.service;

import com.svalero.bikesapi.domain.Bike;
import com.svalero.bikesapi.domain.Route;
import com.svalero.bikesapi.domain.User;
import com.svalero.bikesapi.domain.dto.RouteDTO;
import com.svalero.bikesapi.exception.BikeNotFoundException;
import com.svalero.bikesapi.exception.UserNotFoundException;
import com.svalero.bikesapi.repository.BikeRepository;
import com.svalero.bikesapi.repository.RouteRepository;
import com.svalero.bikesapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BikeRepository bikeRepository;

    @Override
    public Flux<Route> findAllRoutes() {
        return null;
    }

    @Override
    public Mono<Route> addRoute(RouteDTO routeDto) throws UserNotFoundException,
            BikeNotFoundException {
        Mono<User> monoUser = userRepository.findById(routeDto.getUserId()).onErrorReturn(new User());
        Mono<Bike> monoBike = bikeRepository.findById(routeDto.getBikeId()).onErrorReturn(new Bike());

        User user = monoUser.block();
        Bike bike = monoBike.block();

        ModelMapper mapper = new ModelMapper();
        Route route = mapper.map(routeDto, Route.class);
        route.setBike(bike);
        route.setUser(user);
        return routeRepository.save(route);
    }

    @Override
    public Flux<Route> findRoutes(Bike bike, int distance) {
        return routeRepository.findByBikeAndKilometers(bike, distance);
    }

    @Override
    public Flux<Route> findRoutes(Bike bike) {
        return routeRepository.findByBike(bike);
    }
}
