package com.codeandcoke.amazonapi.controller;

import com.codeandcoke.amazonapi.domain.User;
import com.codeandcoke.amazonapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // Register user
    @PostMapping(value = "/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // TODO Iniciar sesión (seguridad)

    // TODO Cerrar sesión (seguridad)
}
