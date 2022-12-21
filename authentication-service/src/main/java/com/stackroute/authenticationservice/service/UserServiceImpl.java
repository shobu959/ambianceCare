package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.Exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.Exception.UserNotFoundException;
import com.stackroute.authenticationservice.repository.UserRepository;
import com.stackroute.authenticationservice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        User savedUser = new User();
        Optional<User> saveUser = userRepository.findById(user.getUserEmail());

        if (saveUser.isPresent()) {
            throw new UserAlreadyExistsException("User with this email is already registered");
        }
        savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User findUserByUserEmail(String userEmail) throws UserNotFoundException {
        User user = null;
        try {
            user = userRepository.findById(userEmail).orElse(null);
            if (user != null) {
                return user;
            } else {
                throw new UserNotFoundException("User is not present");
            }
        } catch (Exception ex) {
            log.error("exception occur" + ex.getMessage());
        }
        return user;
    }


    @Override
    public boolean validateUser(User user) throws UserNotFoundException {
        User retriveUser = findUserByUserEmail(user.getUserEmail());
        if (user.getPassword().equals(retriveUser.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}


