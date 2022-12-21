package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.Exception.CustomerException;
import com.stackroute.authenticationservice.Exception.ExpertException;
import com.stackroute.authenticationservice.Exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.Exception.UserNotFoundException;
import com.stackroute.authenticationservice.entity.Customer;
import com.stackroute.authenticationservice.entity.Expert;
import com.stackroute.authenticationservice.entity.User;
import com.stackroute.authenticationservice.repository.CustomerRepository;
import com.stackroute.authenticationservice.repository.ExpertRepository;
import com.stackroute.authenticationservice.repository.UserRepository;
import com.stackroute.authenticationservice.service.JwtTokenGenerator;
import com.stackroute.authenticationservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    ExpertRepository expertRepository;
    @Autowired
    CustomerRepository customerRepository;

    private UserService userService;
    private JwtTokenGenerator jwtTokenGenerator;

    public UserController(UserService userService, JwtTokenGenerator jwtTokenGenerator,
                          CustomerRepository customerRepository,
                          ExpertRepository expertRepository) {
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.customerRepository = customerRepository;
        this.expertRepository = expertRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {
        ResponseEntity responseEntity = null;
        Map<String, String> tokenMap = null;
        try {
            boolean result = userService.validateUser(user);
            if (result) {
                tokenMap = jwtTokenGenerator.generateToken(user);
            }
            responseEntity = new ResponseEntity<>(tokenMap, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) throws UserAlreadyExistsException {
        try {
            User savedProfile = userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registerCustomer")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
        try {
            if(customer==null){
                throw new CustomerException("Customer data is not proper in request");
            }
            Customer customerdata = customerRepository.save(customer);
            User user = new User();
            user.setUserEmail(customer.getUserEmail());
            user.setPassword(customer.getUserPassword());
            userRepository.save(user);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registerExpert")
    public ResponseEntity<?> saveExpert(@Valid @RequestBody Expert expert) throws ExpertException {
        try {
            if(expert==null){
                throw new ExpertException("Expert data is not proper in request");
            }
            Expert expertrdata = expertRepository.save(expert);
            User user = new User();
            user.setUserEmail(expert.getUserEmail());
            user.setPassword(expert.getUserPassword());
            userRepository.save(user);
            return new ResponseEntity<>(expertrdata, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
