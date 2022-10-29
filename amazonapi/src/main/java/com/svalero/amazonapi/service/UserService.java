package com.svalero.amazonapi.service;

import com.svalero.amazonapi.domain.User;
import com.svalero.amazonapi.exception.UserNotFoundException;

public interface UserService {

    User addUser(User user);
    User findUser(long id) throws UserNotFoundException;
}
