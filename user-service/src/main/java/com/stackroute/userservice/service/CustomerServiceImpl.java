package com.stackroute.userservice.service;

import com.stackroute.userservice.config.MessageConfiguration;
import com.stackroute.userservice.dao.CustomerRepository;
import com.stackroute.userservice.dto.Customer;
import com.stackroute.userservice.exception.CustomerNotFoundException;
import com.stackroute.userservice.exception.ExpertNotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    CustomerRepository customerRepo;

    @Override
    public Customer createNewCustomer(Customer customer) {
        rabbitTemplate.convertAndSend(MessageConfiguration.MESSAGE_EXCHANGE,
                MessageConfiguration.ROUTING_KEY, customer);
        System.out.println("succesfull");
      return this.customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer getCustomerByuserEmail(String userEmail) throws CustomerNotFoundException {
        Customer customer = this.customerRepo.findCustomerByUserEmail(userEmail);
        if (customer == null)
            throw new CustomerNotFoundException("Customer not found with userEmail:" + userEmail);
        return customer;


    }
}


