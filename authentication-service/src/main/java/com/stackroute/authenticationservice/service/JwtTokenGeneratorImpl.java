package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.entity.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class JwtTokenGeneratorImpl implements JwtTokenGenerator{

    private UserRepository userRepository;

    @Autowired
    public JwtTokenGeneratorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Map<String, String> generateToken(User user) {
        log.debug("Inside generate token");
        String jwtToken="";
        jwtToken= Jwts.builder().setSubject(user.getUserEmail()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"secretkey").compact();
        Map<String, String> tokenMap=new HashMap<>();
        tokenMap.put("token",jwtToken);
        User userExists = userRepository.findById(user.getUserEmail()).get();
       // tokenMap.put("userRole",userExists.getUserRole().toString());
        return tokenMap;
    }
}
