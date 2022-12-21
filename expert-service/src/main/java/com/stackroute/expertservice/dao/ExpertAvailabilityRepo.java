package com.stackroute.expertservice.dao;
import com.stackroute.expertservice.entity.ExpertAvailability;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
public interface ExpertAvailabilityRepo extends MongoRepository<ExpertAvailability, Integer> {
    @Query("{'_id' : ?0}")
    ExpertAvailability findExpertByServiceId(String serviceId);
    @Query("{'expertEmail' : ?0}")
    ExpertAvailability findExpertByEmailId(String emailId);
    @Query("{ serviceName : { $regex : ?0 } }")
    List<ExpertAvailability> findExpertByServiceName(String serviceName);
    @Query(value = "{'_id' : ?0}", delete = true)
    ExpertAvailability deleteExpertBYserviceId(String serviceid);
}
