package com.codeandcoke.library.service;

import com.codeandcoke.library.domain.Role;
import com.codeandcoke.library.domain.User;
import com.codeandcoke.library.domain.UserDTO;
import com.codeandcoke.library.repository.RoleRepository;
import com.codeandcoke.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    @Override
//    public boolean add(User user) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setCreationDate(LocalDate.now());
//        user.setActive(true);
//        Role userRole = roleRepository.findByName("user");
//        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
//        userRepository.save(user);
//
//        return true;
//    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }

    @Override
    public Set<User> findAll() {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Set<User> findByCity(String city) {
        return null;
    }

    @Override
    public User addUser(UserDTO userDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setEmail("default@email.com");

        return userRepository.save(user);
    }
}
