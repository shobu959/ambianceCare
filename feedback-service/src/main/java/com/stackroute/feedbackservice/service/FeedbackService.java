package com.stackroute.feedbackservice.service;

import com.stackroute.feedbackservice.entity.Feedback;
import com.stackroute.feedbackservice.exception.FeedbackException;

import java.util.List;

public interface FeedbackService {
    public Feedback getFeedbackDetails(String bookingId, String customerEmail) throws FeedbackException;

    public List<Feedback> getAllFeedbackDetails() throws FeedbackException;

    public List<Feedback> saveFeedbackDetails(Feedback feedback) throws FeedbackException;


}
