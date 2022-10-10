package com.svalero.bikesapi.controller;

import com.svalero.bikesapi.domain.Bike;
import com.svalero.bikesapi.domain.Route;
import com.svalero.bikesapi.domain.dto.RouteDTO;
import com.svalero.bikesapi.exception.BikeNotFoundException;
import com.svalero.bikesapi.exception.ErrorResponse;
import com.svalero.bikesapi.service.BikeService;
import com.svalero.bikesapi.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.svalero.bikesapi.exception.Constants.MANDATORY_FIELD_ERROR_CODE;

@RestController
public class BikeController {

    private final Logger logger = LoggerFactory.getLogger(BikeController.class);

    @Autowired
    private BikeService bikeService;
    @Autowired
    private RouteService routeService;

    @GetMapping("/bikes")
    public ResponseEntity<Flux<Bike>> getBikesByStationId(@RequestParam(name = "station", defaultValue = "0") int stationId) {
        Flux<Bike> bikes;

        if (stationId == 0) {
            bikes = bikeService.findAllBikes();
        } else {
            bikes = bikeService.findAllBikes(stationId);
        }
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/bike/{id}")
    public ResponseEntity<Mono<Bike>> getBike(@PathVariable String id) throws BikeNotFoundException {
        Mono<Bike> bike = bikeService.findBike(id);
        return ResponseEntity.ok(bike);
    }

    @DeleteMapping("/bike/{id}")
    public ResponseEntity<Mono<Bike>> removeBike(@PathVariable String id) throws BikeNotFoundException {
        Mono<Bike> bike = bikeService.deleteBike(id);
        return ResponseEntity.ok(bike);
    }

    @PostMapping("/bikes")
    public ResponseEntity<Mono<Bike>> addBike(@Valid @RequestBody Bike bike) {
        Mono<Bike> newBike = bikeService.addBike(bike);
        return ResponseEntity.ok(newBike);
    }

    @PutMapping("/bike/{id}")
    public ResponseEntity<Mono<Bike>> modifyBike(@RequestBody Bike bike, @PathVariable String id) throws BikeNotFoundException {
        // TODO Falta controlar algún error 400
        Mono<Bike> newBike = bikeService.modifyBike(id, bike);
        return ResponseEntity.ok(newBike);
    }

    // Las rutas de una bicicleta concreta
    @GetMapping("/bike/{bikeId}/routes")
    public ResponseEntity<Flux<Route>> getRoutes(@PathVariable String bikeId,
                           @RequestParam(name = "distance", defaultValue = "0") int distance)
                        throws BikeNotFoundException {
        logger.info("begin getRoutes");
        Flux<Route> routeList = null;

        logger.info("Se busca bike: " + bikeId);
        Mono<Bike> bike = bikeService.findBike(bikeId);
        logger.info("bike encontrada: " + bike.block().getId());
        if (distance != 0) {
            routeList = routeService.findRoutes(bike.block(), distance);
        } else {
            routeList = routeService.findRoutes(bike.block());
        }

        logger.info("end getRoutes");
        return ResponseEntity.ok(routeList);
    }

    @ExceptionHandler(BikeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBikeNotFoundException(BikeNotFoundException bnfe) {
        ErrorResponse errorResponse = ErrorResponse.generalError(101, bnfe.getMessage());
        logger.error(bnfe.getMessage(), bnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // TODO Más tipos de excepciones que puedan generar errores

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = ErrorResponse.generalError(999, "Internal server error");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return ResponseEntity.badRequest().body(ErrorResponse.validationError(errors));
    }
}
