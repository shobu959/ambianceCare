package com.stackroute.customerservice.repository;

import com.stackroute.customerservice.entity.CustomerBooking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerBookingRepository extends MongoRepository<CustomerBooking,String> {

    public CustomerBooking findCustomerBookingByBookingId(String productId);
}
