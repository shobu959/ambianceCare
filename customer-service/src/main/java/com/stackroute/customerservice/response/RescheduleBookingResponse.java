package com.stackroute.customerservice.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RescheduleBookingResponse {
    private String bookingId;
    private String serviceId;
    private String serviceName;
    private Double serviceCost;
    private Integer cartValue;
    private String bookedDate;
    private String bookedSlot;
    private String customerAddress;
    private String pincode;
    private String expertEmail;
    private String customerEmail;
    private String statusMessage;
    private String statusCode;
    private String bookingStatus;
}
