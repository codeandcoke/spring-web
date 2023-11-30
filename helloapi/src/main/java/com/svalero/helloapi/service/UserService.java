package com.svalero.helloapi.service;

import com.svalero.helloapi.domain.User;
import com.svalero.helloapi.exception.UserNotFoundException;
import com.svalero.helloapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    // TODO Hacer el resto de métodos hasta el CRUD
}
