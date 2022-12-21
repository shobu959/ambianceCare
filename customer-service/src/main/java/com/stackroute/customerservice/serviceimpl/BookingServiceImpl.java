package com.stackroute.customerservice.serviceimpl;

import com.stackroute.customerservice.config.MessageRescheduleConfiguration;
import com.stackroute.customerservice.entity.CustomerBooking;
import com.stackroute.customerservice.entity.Services;
import com.stackroute.customerservice.entity.Status;
import com.stackroute.customerservice.exception.CustomerException;
import com.stackroute.customerservice.repository.CustomerBookingRepository;
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
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private CustomerBookingRepository customerBookingRepository;

    @Autowired
    private GenerateIdServiceImpl generateIdServiceImpl;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public CancelBookingResponse updateOrderStatus(CancelBookingRequest request) {
        logger.info("updateOrderStatus() initiated");
        CancelBookingResponse cancelBookingResponse = new CancelBookingResponse();
        if (request.getBookingId() != null && request!=null) {
            logger.info("Fetching details for updating the status");
            CustomerBooking booking = customerBookingRepository.findCustomerBookingByBookingId(request.getBookingId());
            logger.info("Details fetched successfully: ", booking);
            if (booking != null) {
                Status status = Status.CANCELED;
                logger.info("Setting the status to canceled");
                booking.setBookedStatus(String.valueOf(status));
                customerBookingRepository.save(booking);
            }
            cancelBookingResponse.setBookingId(request.getBookingId());
            cancelBookingResponse.setBookedSlot(request.getBookedSlot());
            cancelBookingResponse.setBookedDate(request.getBookedDate());
            cancelBookingResponse.setPincode(request.getPincode());
            cancelBookingResponse.setCustomerAddress(request.getCustomerAddress());
            cancelBookingResponse.setServiceId(request.getServiceId());
            cancelBookingResponse.setServiceName(request.getServiceName());
            cancelBookingResponse.setServiceCost(request.getServiceCost());
            cancelBookingResponse.setCartValue(request.getCartValue());
            cancelBookingResponse.setCustomerEmail(request.getCustomerEmail());
            cancelBookingResponse.setExpertEmail(request.getExpertEmail());
            cancelBookingResponse.setStatusCode("200");
            cancelBookingResponse.setStatusMessage("OK");
            cancelBookingResponse.setBookingStatus("CANCELLED");
            rabbitTemplate.convertAndSend(MessageRescheduleConfiguration.MESSAGE_EXCHANGE,
                    MessageRescheduleConfiguration.CANCEL_ROUTING_KEY,cancelBookingResponse);
        }
        logger.info("updateOrderStatus() completed");
        return cancelBookingResponse;
    }

    @Override
    public List<Services> fetchAllServices() throws CustomerException {
        logger.info("fetchAllServices() initiated");
        List<Services> servicesList = servicesRepository.findAll();
        logger.info("ServiceList: ", servicesList.toArray().toString());
        if (servicesList.isEmpty()) {
            logger.error("Exception caught in while fetching services");
            throw new CustomerException("Exception caught while fetching services as no services are there to show");
        }
        logger.info("fetchAllServices() completed");
        return servicesList;

    }

    @Override
    public CartTotalResponse calculateTotalAmount(CartTotalRequest cartTotalRequest) throws CustomerException {
        logger.info("calculateTotalAmount() initiated");
        CartTotalResponse cartTotalResponse = new CartTotalResponse();
        Double totalAmount = 0.0;
        logger.info("Fetching details for calculating the total cart amount");
        CustomerBooking booking = customerBookingRepository.findCustomerBookingByBookingId(cartTotalRequest.getBookingId());
        logger.info("Details for calculating: ", booking);
        if (booking == null || booking.getServiceCost() == 0.0 || booking.getCartValue() == 0)
            throw new CustomerException("Exception caught while fetching data for total calculation");

        totalAmount = booking.getServiceCost() * booking.getCartValue();
        logger.info("TotalAmount of cart: ", totalAmount);
        cartTotalResponse.setTotalAmount(totalAmount);
        cartTotalResponse.setCustomerEmail(cartTotalRequest.getCustomerEmail());
        cartTotalResponse.setBookingId(cartTotalResponse.getBookingId());
        rabbitTemplate.convertAndSend(MessageRescheduleConfiguration.MESSAGE_EXCHANGE,
                MessageRescheduleConfiguration.CART_ROUTING_KEY,cartTotalResponse);
        logger.info("calculateTotalAmount() completed");
        return cartTotalResponse;
    }

    @Override
    public RescheduleBookingResponse rescheduleBooking(RescheduleRequest rescheduleRequest) throws CustomerException {
        logger.info("rescheduleBooking() initiated");
        RescheduleBookingResponse rescheduleBookingResponse = new RescheduleBookingResponse();
        if (rescheduleRequest == null) {
            throw new CustomerException("Exception caught while fetching details from request while rescheduling service");
        }
        if (rescheduleRequest != null && rescheduleRequest!=null) {
            logger.info("Fetching details for rescheduling booking");
            CustomerBooking booking = customerBookingRepository.findCustomerBookingByBookingId(rescheduleRequest.getBookingId());
            logger.info("Rescheduling booking: ", booking);
            if (booking == null) {
                rescheduleBookingResponse.setStatusMessage("FAILED");
                rescheduleBookingResponse.setStatusCode("500");
                throw new CustomerException("Exception caught for Customer slot rescheduling. Please check the details");
            } else {
                Status status = Status.RESCHEDULED;
                booking.setBookedStatus(String.valueOf(status));
                booking.setBookedDate(rescheduleRequest.getBookedDate());
                booking.setBookingSlot(rescheduleRequest.getBookedSlot());
                customerBookingRepository.save(booking);
                logger.info("Saved data");
                rescheduleBookingResponse.setServiceId(rescheduleRequest.getServiceId());
                rescheduleBookingResponse.setServiceName(rescheduleRequest.getServiceName());
                rescheduleBookingResponse.setServiceCost(rescheduleRequest.getServiceCost());
                rescheduleBookingResponse.setPincode(rescheduleRequest.getPincode());
                rescheduleBookingResponse.setBookingId(rescheduleRequest.getBookingId());
                rescheduleBookingResponse.setBookedSlot(rescheduleRequest.getBookedSlot());
                rescheduleBookingResponse.setBookedDate(rescheduleRequest.getBookedDate());
                rescheduleBookingResponse.setCustomerAddress(rescheduleRequest.getCustomerAddress());
                rescheduleBookingResponse.setCustomerEmail(rescheduleRequest.getCustomerEmail());
                rescheduleBookingResponse.setExpertEmail(rescheduleRequest.getExpertEmail());
                rescheduleBookingResponse.setBookingStatus("RESCHEDULED");
                rescheduleBookingResponse.setStatusMessage("OK");
                rescheduleBookingResponse.setCartValue(rescheduleRequest.getCartValue());
                rescheduleBookingResponse.setStatusCode("200");
                rabbitTemplate.convertAndSend(MessageRescheduleConfiguration.RESCHEDULE_MESSAGE_EXCHANGE,
                        MessageRescheduleConfiguration.RESCHEDULE_ROUTING_KEY,rescheduleBookingResponse);
            }
        }
        logger.info("rescheduleBooking() completed");
        return rescheduleBookingResponse;
    }

    @Override
    public BookServiceResponse bookService(BookServiceRequest bookServiceRequest) throws CustomerException {
        logger.info("bookService() method initiated");
        BookServiceResponse bookServiceResponse = new BookServiceResponse();
        if (bookServiceRequest == null) {
            throw new CustomerException("Exception caught while fetching details from request while rescheduling service");
        }
        Status status = Status.BOOKED;
        CustomerBooking customerBooking = new CustomerBooking();
        logger.info("Creating object of CustomerBooking");
        customerBooking.setBookingId(generateIdServiceImpl.generateString());
        customerBooking.setBookingSlot(bookServiceRequest.getBookedSlot());
        customerBooking.setBookedDate(bookServiceRequest.getBookedDate());
        customerBooking.setExpertEmail(bookServiceRequest.getExpertEmail());
        customerBooking.setServiceName(bookServiceRequest.getServiceName());
        customerBooking.setServiceId(bookServiceRequest.getServiceId());
        customerBooking.setCustomerEmail(bookServiceRequest.getCustomerEmail());
        customerBooking.setCustomerAddress(bookServiceRequest.getCustomerAddress());
        customerBooking.setPincode(bookServiceRequest.getPincode());
        customerBooking.setBookedStatus(String.valueOf(status));
        customerBooking.setServiceCost(bookServiceRequest.getServiceCost());
        customerBooking.setCartValue(bookServiceRequest.getCartValue());
        logger.info("Saving details for new CustomerBooking");
        customerBookingRepository.save(customerBooking);
        logger.info("Details for new CustomerBooking saved successfully");

        String id = customerBooking.getBookingId();

        logger.info("Setting values in response for service communication and response");
        bookServiceResponse.setBookingId(customerBooking.getBookingId());
        bookServiceResponse.setServiceId(bookServiceRequest.getServiceId());
        bookServiceResponse.setServiceName(bookServiceRequest.getServiceName());
        bookServiceResponse.setExpertEmail(bookServiceRequest.getExpertEmail());
        bookServiceResponse.setCustomerEmail(bookServiceRequest.getCustomerEmail());
        bookServiceResponse.setBookedSlot(bookServiceRequest.getBookedSlot());
        bookServiceResponse.setBookedDate(bookServiceRequest.getBookedDate());
        bookServiceResponse.setServiceCost(bookServiceRequest.getServiceCost());
        bookServiceResponse.setPincode(bookServiceRequest.getPincode());
        bookServiceResponse.setCustomerAddress(bookServiceRequest.getCustomerAddress());
        bookServiceResponse.setCartValue(bookServiceRequest.getCartValue());
        bookServiceResponse.setStatusCode("200");
        bookServiceResponse.setStatusMessage("OK");
        rabbitTemplate.convertAndSend(MessageRescheduleConfiguration.MESSAGE_EXCHANGE,
                MessageRescheduleConfiguration.ROUTING_KEY,bookServiceResponse);
        logger.info("Values in response for service communication and response saved successfully!!");
        logger.info("bookService() method initiated");
        return bookServiceResponse;
    }

    @Override
    public CustomerBooking addService(CustomerBooking customerBooking) {
        logger.info("addService() initiated");
        CustomerBooking customerBook = new CustomerBooking();
        logger.info("Saving details for new service");
        customerBookingRepository.save(customerBooking);
        logger.info("Details saved successfully");
        CustomerBooking booking = customerBookingRepository.findCustomerBookingByBookingId(customerBooking.getBookingId());
        logger.info("addService() completed");
        return booking;
    }

    @Override
    public CustomerBooking updateCartValue(Integer cartValue, String bookingId) throws CustomerException {
        logger.info("updateCartValue() initiated");
        CustomerBooking booking = customerBookingRepository.findCustomerBookingByBookingId(bookingId);
        logger.info("Fetching details for updating cart value: ", booking);
        if (booking == null) {
            throw new CustomerException("Exception caught while fetching details for customer for updating cart value");
        }
        booking.setCartValue(booking.getCartValue() + cartValue);
        customerBookingRepository.save(booking);
        logger.info("updateCartValue() completed");
        return booking;
    }

    @Override
    public Services fetchParticularService(String serviceId) throws CustomerException {
        logger.info("fetchParticularService() initiated");
        Services services = servicesRepository.findServicesByServiceId(serviceId);
        if(services==null){
            throw new CustomerException("Exception caught while fetching details for searching service via serviceId");
        }
        logger.info("fetchParticularService() completed");
        return services;

    }


}
