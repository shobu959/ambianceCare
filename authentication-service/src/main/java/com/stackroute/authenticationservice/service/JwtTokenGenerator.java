package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.entity.User;

import java.util.Map;

public interface JwtTokenGenerator {
    Map<String,String> generateToken(User user);
}
