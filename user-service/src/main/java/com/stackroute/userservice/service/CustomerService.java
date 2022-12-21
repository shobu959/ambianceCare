package com.stackroute.userservice.service;


import com.stackroute.userservice.dto.Customer;
import com.stackroute.userservice.exception.CustomerNotFoundException;

import java.util.Optional;

public interface CustomerService {

    Customer createNewCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer getCustomerByuserEmail(String userEmail) throws CustomerNotFoundException;



}
