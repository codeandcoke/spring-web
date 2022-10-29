package com.svalero.amazonapi.service;

import com.svalero.amazonapi.domain.User;
import com.svalero.amazonapi.exception.UserNotFoundException;
import com.svalero.amazonapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // TODO Modificar cuando se implemente la seguridad
    @Override
    public User findUser(long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
