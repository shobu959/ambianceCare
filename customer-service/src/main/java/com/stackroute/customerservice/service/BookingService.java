package com.stackroute.customerservice.service;

import com.stackroute.customerservice.entity.CustomerBooking;
import com.stackroute.customerservice.entity.Services;
import com.stackroute.customerservice.exception.CustomerException;
import com.stackroute.customerservice.request.BookServiceRequest;
import com.stackroute.customerservice.request.CancelBookingRequest;
import com.stackroute.customerservice.request.CartTotalRequest;
import com.stackroute.customerservice.request.RescheduleRequest;
import com.stackroute.customerservice.response.BookServiceResponse;
import com.stackroute.customerservice.response.CancelBookingResponse;
import com.stackroute.customerservice.response.CartTotalResponse;
import com.stackroute.customerservice.response.RescheduleBookingResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public interface BookingService {

    public CancelBookingResponse updateOrderStatus(CancelBookingRequest cancelBookingRequest);

    public List<Services> fetchAllServices() throws CustomerException;

    public CartTotalResponse calculateTotalAmount(CartTotalRequest cartTotalRequest) throws CustomerException;

    public RescheduleBookingResponse rescheduleBooking(RescheduleRequest rescheduleRequest) throws CustomerException;

    public BookServiceResponse bookService(BookServiceRequest bookServiceRequest) throws CustomerException;

    public CustomerBooking addService(CustomerBooking customerBooking);

    public CustomerBooking updateCartValue(Integer cartValue, String bookingId) throws CustomerException;

    public Services fetchParticularService(String serviceId) throws CustomerException;

    }