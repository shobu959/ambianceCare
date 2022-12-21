package com.stackroute.customerservice.repository;


import com.stackroute.customerservice.entity.Services;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ServicesRepository extends MongoRepository<Services, Integer> {

    public Services findServicesByServiceId(String sericeId);

}
