package com.stackroute.userservice.dao;

import com.stackroute.userservice.dto.Customer;
import com.stackroute.userservice.dto.Expert;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {

    public Customer findCustomerByUserEmail(String email);
}
