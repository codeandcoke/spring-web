package com.svalero.games.service;

import com.svalero.games.domain.User;
import com.svalero.games.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        user.setRegisterDate(LocalDate.now());
        return userRepository.save(user);
    }
}
