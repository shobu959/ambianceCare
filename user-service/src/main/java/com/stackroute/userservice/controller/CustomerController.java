package com.stackroute.userservice.controller;

import com.stackroute.userservice.dto.Customer;
import com.stackroute.userservice.exception.CustomerNotFoundException;
import com.stackroute.userservice.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
     CustomerService customerService;

    @PostMapping("customer")
    public Customer createNewCustomer(@RequestBody @Valid Customer customer){

        return this.customerService.createNewCustomer(customer);
    }

    @PutMapping("customer")
    public Customer updateCustomer(@RequestBody Customer customer){
        return this.customerService.updateCustomer(customer);
    }
    @GetMapping("customer/{userEmail}")
    public Customer getCustomerByEmail(@PathVariable ("userEmail") String userEmail) throws CustomerNotFoundException {
        return this.customerService.getCustomerByuserEmail(userEmail);
    }

}




