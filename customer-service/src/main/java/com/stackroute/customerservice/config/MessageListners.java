package com.stackroute.customerservice.config;

import com.stackroute.customerservice.response.RescheduleBookingResponse;
import com.stackroute.customerservice.serviceimpl.BookingServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListners {

//    public static final String QUEUE = "expert_queue";
//    @Autowired
//    private BookingServiceImpl bookingService;
//
//    @RabbitListener(queues = QUEUE)
//    public void getDatafromListner(RescheduleBookingResponse rescheduleBookingResponse) {
////        bookingService.rescheduleBooking(rescheduleBookingResponse);
//        System.out.println(rescheduleBookingResponse.toString());
//    }
}
