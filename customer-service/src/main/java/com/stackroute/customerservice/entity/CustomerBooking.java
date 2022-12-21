package com.stackroute.customerservice.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer")
public class CustomerBooking {

    @Id
    private String bookingId;
    private String bookedDate;

    private String bookingSlot;
    private String expertEmail;
    private String customerEmail;
    private String customerAddress;
    private String pincode;
    private String bookedStatus;

    private String serviceId;

    private String serviceName;

    private Double serviceCost;

    private Integer cartValue;




}
