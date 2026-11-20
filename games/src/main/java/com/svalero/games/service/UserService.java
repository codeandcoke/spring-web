package com.svalero.games.service;

import com.svalero.games.domain.Game;
import com.svalero.games.domain.User;
import com.svalero.games.exception.UserNotFoundException;
import com.svalero.games.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        user.setRegisterDate(LocalDate.now());
        return userRepository.save(user);
    }

    public List<Game> getGames(long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return user.getGames();
    }
}
