package com.stackroute.feedbackservice.controller;

import com.stackroute.feedbackservice.entity.Feedback;
import com.stackroute.feedbackservice.exception.FeedbackException;
import com.stackroute.feedbackservice.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FeedbackController {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/getFeedback")
    public Feedback getFeedbackDetails(@Valid @RequestParam("bookingId") String bookingId, @RequestParam("customerEmail")
    String customerEmail) throws FeedbackException {
        logger.info("getFeedbackDetails() inside controller initiated");
        Feedback feedback = feedbackService.getFeedbackDetails(bookingId, customerEmail);
        logger.info("getFeedbackDetails() inside controller completed");
        return feedback;
    }

    @GetMapping("/getAllFeedback")
    public List<Feedback> getAllFeedbackDetails() throws FeedbackException {
        logger.info("getAllFeedbackDetails() inside controller initiated");
        List<Feedback> feedback = feedbackService.getAllFeedbackDetails();
        logger.info("getAllFeedbackDetails() inside controller completed");

        return feedback;
    }

    @PostMapping("/addFeedback")
    public List<Feedback> saveFeedbackDetails(@Valid @RequestBody Feedback feedback) throws FeedbackException {
        logger.info("saveFeedbackDetails() inside controller initiated");
        List<Feedback> feedbackList = feedbackService.saveFeedbackDetails(feedback);
        logger.info("saveFeedbackDetails() inside controller completed");
        return feedbackList;
    }

}
