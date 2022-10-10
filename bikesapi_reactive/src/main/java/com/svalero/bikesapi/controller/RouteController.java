package com.svalero.bikesapi.controller;

import com.svalero.bikesapi.domain.Route;
import com.svalero.bikesapi.domain.User;
import com.svalero.bikesapi.domain.dto.RouteDTO;
import com.svalero.bikesapi.exception.BikeNotFoundException;
import com.svalero.bikesapi.exception.ErrorResponse;
import com.svalero.bikesapi.exception.UserNotFoundException;
import com.svalero.bikesapi.service.RouteService;
import com.svalero.bikesapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class RouteController {

    private final Logger logger = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private RouteService routeService;

    @PostMapping("/routes")
    public ResponseEntity<?> addRoute(@Valid @RequestBody RouteDTO routeDto) throws UserNotFoundException,
            BikeNotFoundException {
        Mono<Route> route = routeService.addRoute(routeDto);
        return ResponseEntity.ok(route);
    }
}
