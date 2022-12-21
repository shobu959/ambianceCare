package com.stackroute.customerservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CancelBookingResponse {

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
