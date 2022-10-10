package com.svalero.bikesapi.service;

import com.svalero.bikesapi.domain.User;
import com.svalero.bikesapi.exception.UserNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Flux<User> findAllUsers();
    Mono<User> findUser(long id) throws UserNotFoundException;

    Mono<User> addUser(User user);
}
