package com.codeandcoke.amazonapi.service;

import com.codeandcoke.amazonapi.domain.User;
import com.codeandcoke.amazonapi.exception.UserNotFoundException;

public interface UserService {

    User addUser(User user);
    User findUser(long id) throws UserNotFoundException;
}
