package com.stackroute.userservice.service;

import com.stackroute.userservice.dto.Expert;
import com.stackroute.userservice.exception.ExpertNotFoundException;

import java.util.Optional;

public interface ExpertService {
    Expert createNewExpert(Expert expert);
    Expert updateExpert(Expert expert);
    Expert getExpertByuserEmail(String userEmail) throws ExpertNotFoundException;
}
