package com.stackroute.feedbackservice.serviceimpl;

import com.stackroute.feedbackservice.entity.Feedback;
import com.stackroute.feedbackservice.exception.FeedbackException;
import com.stackroute.feedbackservice.repository.FeedbackRepository;
import com.stackroute.feedbackservice.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private GenerateIdServiceImpl generateIdService;

    @Override
    public Feedback getFeedbackDetails(String bookingId, String customerEmail) throws FeedbackException {
        logger.info("getFeedbackDetails() initiated");
        Feedback feedback = feedbackRepository.findFeedbackByCustomerEmail(customerEmail);
        if (feedback == null)
            throw new FeedbackException("No feedbacks present for this particular emailId" + customerEmail);
        logger.info("getFeedbackDetails() completed");
        return feedback;
    }

    @Override
    public List<Feedback> getAllFeedbackDetails() throws FeedbackException {
        logger.info("getAllFeedbackDetails() initiated");
        List<Feedback> feedbackList = this.feedbackRepository.findAll();
        if (feedbackList == null)
            throw new FeedbackException("No feedbacks present in our system");
        logger.info("getAllFeedbackDetails() completed");
        return feedbackList;
    }

    @Override
    public List<Feedback> saveFeedbackDetails(Feedback feedback) throws FeedbackException {
        logger.info("saveFeedbackDetails() initiated");
        if (feedback.getCustomerEmail() == null) {
            throw new FeedbackException("No emailID present in request");
        }
        Feedback feedback1 = new Feedback();
        feedback1.setFeedbackId(generateIdService.generateString());
        feedback1.setBookingId(feedback.getBookingId());
        feedback1.setCustomerRating(feedback.getCustomerRating());
        feedback1.setCustomerComment(feedback.getCustomerComment());
        feedback1.setCustomerEmail(feedback.getCustomerEmail());
        feedbackRepository.save(feedback1);

        List<Feedback> fb = this.feedbackRepository.findByCustomerEmail(feedback.getCustomerEmail());
        if (fb.isEmpty()) {
            throw new FeedbackException("No feedbacks present in our system for");
        }
        logger.info("saveFeedbackDetails() comopleted");
        return fb;

    }
}
