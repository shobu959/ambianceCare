package com.stackroute.authenticationservice.repository;

import com.stackroute.authenticationservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {

    @Query("Select a from Customer a where a.userEmail = :userEmail")
    public Customer getCutomerDetail(String userEmail);
}
