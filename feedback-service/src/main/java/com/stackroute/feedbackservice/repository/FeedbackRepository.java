package com.stackroute.feedbackservice.repository;

import com.stackroute.feedbackservice.entity.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {

    public Feedback findFeedbackByCustomerEmail(String email);

    public List<Feedback> findByCustomerEmail(String email);
}
