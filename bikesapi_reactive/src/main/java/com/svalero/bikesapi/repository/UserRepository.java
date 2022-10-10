package com.svalero.bikesapi.repository;

import com.svalero.bikesapi.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Flux<User> findAll();
}
