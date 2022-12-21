package com.stackroute.authenticationservice.config;

import com.stackroute.authenticationservice.Exception.CustomerException;
import com.stackroute.authenticationservice.Exception.ExpertException;
import com.stackroute.authenticationservice.Exception.UserNotFoundException;
import com.stackroute.authenticationservice.controller.UserController;
import com.stackroute.authenticationservice.entity.Customer;
import com.stackroute.authenticationservice.entity.Expert;
import com.stackroute.authenticationservice.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    UserController userController;

    @RabbitListener(queues ="customer_queue")
    public void getCustomerDataFromRabbitMq(Customer customer) throws CustomerException, UserNotFoundException {
        System.out.println(customer.toString());
        userController.saveCustomer(customer);
        System.out.println("Saved successfully!!");

        System.out.println("Validating a customer");
        User user = new User();
        user.setUserEmail(customer.getUserEmail());
        user.setPassword(customer.getUserPassword());
        userController.authenticateUser(user);
        System.out.println("Validated customer");

    }

    @RabbitListener(queues = "expert_queue")
    public void getExpertDataFromRabbitMq(Expert expert) throws ExpertException, UserNotFoundException {
        System.out.println(expert.toString());
        userController.saveExpert(expert);
        System.out.println("Saved successfully!!");

        System.out.println("Validating a expert");
        User user = new User();
        user.setUserEmail(expert.getUserEmail());
        user.setPassword(expert.getUserPassword());
        userController.authenticateUser(user);
        System.out.println("Validated expert");
    }
}

