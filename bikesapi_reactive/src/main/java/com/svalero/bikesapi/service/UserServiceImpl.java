package com.svalero.bikesapi.service;

import com.svalero.bikesapi.domain.User;
import com.svalero.bikesapi.exception.UserNotFoundException;
import com.svalero.bikesapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Flux<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> findUser(long id) throws UserNotFoundException {
        return null;
    }

    @Override
    public Mono<User> addUser(User user) {
        return userRepository.save(user);
    }
}
