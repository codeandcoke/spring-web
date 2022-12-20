package com.codeandcoke.library.service;

import com.codeandcoke.library.domain.User;
import com.codeandcoke.library.domain.UserDTO;

import java.util.Set;

/**
 * Service para gestión de usuarios
 */
public interface UserService {

    Set<User> findAll();
    User findByUsername(String username);
    Set<User> findByCity(String city);

    User addUser(UserDTO user);
    void remove(User user);

}
