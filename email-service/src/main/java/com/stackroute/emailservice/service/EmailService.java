package com.stackroute.emailservice.service;

import com.stackroute.emailservice.entity.EmailRequest;
import com.stackroute.emailservice.entity.EmailResponse;

public interface EmailService {

     EmailResponse sendBookingMail(EmailRequest request);

     EmailResponse sendCancellationMail(EmailRequest request);
     EmailResponse sendRescheduleMail(EmailRequest request);


}
