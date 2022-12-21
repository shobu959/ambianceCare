package com.stackroute.userservice.dao;

import com.stackroute.userservice.dto.Expert;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ExpertRepository  extends MongoRepository<Expert,String> {
    public Expert findExpertByUserEmail(String email);
}
