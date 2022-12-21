package com.stackroute.userservice.advice;

import com.stackroute.userservice.exception.CustomerNotFoundException;
import com.stackroute.userservice.exception.ExpertNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String>handleInvalidArgument(MethodArgumentNotValidException exception) {

     Map<String,String>errorMap=new HashMap<>();
     exception.getBindingResult().getAllErrors().forEach(error->{errorMap.put(error.getDefaultMessage(),error.getDefaultMessage());
     });
     return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CustomerNotFoundException.class)
    public Map<String,String>handleBusinessException(CustomerNotFoundException exception) {
        Map<String,String>errorMap=new HashMap<>();
    errorMap.put("errorMessage",exception.getMessage());
    return errorMap;
       }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ExpertNotFoundException.class)
    public Map<String,String>handleBusinessException(ExpertNotFoundException exception) {
        Map<String,String>errorMap=new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return errorMap;
    }


}
