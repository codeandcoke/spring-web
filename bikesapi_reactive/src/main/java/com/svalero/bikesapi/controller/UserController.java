package com.svalero.bikesapi.controller;

import com.svalero.bikesapi.domain.User;
import com.svalero.bikesapi.exception.ErrorResponse;
import com.svalero.bikesapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Mono<User>> addUser(@Valid @RequestBody User user) {
        Mono<User> newUser = userService.addUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping(value = "/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getUsers() {
        return userService.findAllUsers().delayElements(Duration.ofSeconds(1));
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
