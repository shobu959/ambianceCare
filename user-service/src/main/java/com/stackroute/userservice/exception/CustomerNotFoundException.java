package com.stackroute.userservice.exception;

import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.validation.Errors;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String msg) {

        super(msg);
    }



}
