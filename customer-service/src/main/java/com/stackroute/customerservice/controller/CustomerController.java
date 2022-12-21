package com.stackroute.customerservice.controller;

import com.stackroute.customerservice.entity.CustomerBooking;
import com.stackroute.customerservice.entity.Services;
import com.stackroute.customerservice.exception.CustomerException;
import com.stackroute.customerservice.repository.ServicesRepository;
import com.stackroute.customerservice.request.BookServiceRequest;
import com.stackroute.customerservice.request.CancelBookingRequest;
import com.stackroute.customerservice.request.CartTotalRequest;
import com.stackroute.customerservice.request.RescheduleRequest;
import com.stackroute.customerservice.response.BookServiceResponse;
import com.stackroute.customerservice.response.CancelBookingResponse;
import com.stackroute.customerservice.response.CartTotalResponse;
import com.stackroute.customerservice.response.RescheduleBookingResponse;
import com.stackroute.customerservice.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private BookingService cancelBookingService;
    @Autowired
    private ServicesRepository servicesRepository;

    @GetMapping("/fetchAllServices")
    public List<Services> fetchAllServices() throws CustomerException {
        logger.info("fetchAllServices() inside controller initiated");
        List<Services> servicesList = cancelBookingService.fetchAllServices();
        logger.info("fetchAllServices() inside controller completed");
        return servicesList;
    }

    @GetMapping("/fetchParticularService")
    public Services fetchParticularService(@Valid @RequestParam("serviceId") String serviceId) throws CustomerException {
        logger.info("fetchParticularService() inside controller initiated");
        Services service = cancelBookingService.fetchParticularService(serviceId);
        logger.info("fetchParticularService() inside controller completed");
        return service;
    }

    @PostMapping("/bookService")
    public BookServiceResponse bookService(@Valid @RequestBody BookServiceRequest bookServiceRequest) throws CustomerException {
        logger.info("bookService() inside controller initiated");
        BookServiceResponse bookServiceResponse = cancelBookingService.bookService(bookServiceRequest);
        logger.info("bookService() inside controller completed");
        return bookServiceResponse;
    }

    @PostMapping("/cartTotal")
    public CartTotalResponse calculateTotalAmount(@Valid @RequestBody CartTotalRequest cartTotalRequest) throws CustomerException {
        logger.info("calculateTotalAmount() inside controller initiated");
        CartTotalResponse totalAmount = cancelBookingService.calculateTotalAmount(cartTotalRequest);
        logger.info("calculateTotalAmount() inside controller completed");
        return totalAmount;
    }

    @PutMapping("/cancelOrder")
    public CancelBookingResponse cancelOrderForCustomer(@Valid @RequestBody CancelBookingRequest cancelBookingRequest) {
        logger.info("cancelOrderForCustomer() inside controller initiated");
        CancelBookingResponse cancelOrderResponse = cancelBookingService.updateOrderStatus(cancelBookingRequest);
        logger.info("cancelOrderForCustomer() inside controller completed");
        return cancelOrderResponse;
    }

    @PutMapping("/rescheduleBooking")
    public RescheduleBookingResponse rescheduleBooking(@Valid @RequestBody RescheduleRequest rescheduleRequest) throws CustomerException {
        logger.info("rescheduleBooking() inside controller initiated");
        RescheduleBookingResponse booking = cancelBookingService.rescheduleBooking(rescheduleRequest);
        logger.info("rescheduleBooking() inside controller completed");

        return booking;
    }

    @PutMapping("/updateCartValue")
    public CustomerBooking updateCart(@ Valid @RequestParam("cartValue") Integer cartValue, @Valid @RequestParam("bookingId") String bookingId) throws CustomerException {
        logger.info("updateCart() inside controller initiated");
        CustomerBooking customerBooking = cancelBookingService.updateCartValue(cartValue,bookingId);
        logger.info("updateCart() inside controller completed");
        return customerBooking;
    }

    @PostMapping("/addService")
    public Services addService(@Valid @RequestParam("id") String serviceId,@Valid @RequestParam("name")
    String serviceName, @RequestParam("cost") Double amount) {
        logger.info("addService() inside controller initiated");
        Services service = new Services();
        service.setServiceId(serviceId);
        service.setServiceName(serviceName);
        service.setServiceCost(amount);
        servicesRepository.save(service);
        logger.info("addService() inside controller completed");
        return service;
    }

}
