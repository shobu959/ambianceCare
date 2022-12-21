package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.Exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.Exception.UserNotFoundException;
import com.stackroute.authenticationservice.entity.User;

public interface UserService {

    User saveUser(User user) throws UserAlreadyExistsException;
    User findUserByUserEmail (String userEmail) throws UserNotFoundException;
    boolean validateUser (User user) throws UserNotFoundException;
}
