package com.stackroute.emailservice.controller;

import com.stackroute.emailservice.entity.EmailRequest;
import com.stackroute.emailservice.entity.EmailResponse;
import com.stackroute.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    //@RequestMapping("/v1.0.0")

    @PostMapping("bookingMail")
    public EmailResponse sendBookingEmail(@Valid @RequestBody EmailRequest emailRequest){


        EmailResponse bookingMailResponse = emailService.sendBookingMail(emailRequest);
        return bookingMailResponse;
    }

    @PostMapping("cancellationMail")
    public EmailResponse sendCancellationEmail(@Valid@RequestBody EmailRequest request )
    {
        EmailResponse response = emailService.sendCancellationMail(request);
        return response;

    }

    @PostMapping("rescheduleMail")
    public EmailResponse sendRescheduledMail(@Valid@RequestBody EmailRequest request )
    {
        EmailResponse response = emailService.sendRescheduleMail(request);
        return response;

    }
}
