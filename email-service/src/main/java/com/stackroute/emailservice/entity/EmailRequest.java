package com.stackroute.emailservice.entity;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class EmailRequest {

    @NotNull(message = "BookingID can't be Null.")
    @NotBlank(message = "BookingID can't be blank.")
    private String bookingId;

    @NotNull(message = "Date cannot be null.")
    @FutureOrPresent(message = "Booking date cannot be earlier than current date. ")
    private LocalDate bookedDate;

    private String bookingSlot;
    @Email(message="Email should be valid.")
    private String expertEmail;

    @Email
    private String customerEmail;

    @NotBlank(message = "Address cannot be Blank.")
    @NotNull(message = "Address cannot be null.")
    private String customerAddress;

    @NotBlank(message = "PinCode cannot be Blank.")
    @NotBlank (message = "PinCode cannot be null.")
    @Pattern(regexp="^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}",message="Pincode format incorrect.")
    private String pincode;

    @NotBlank(message = "BookedStatus cannot be Blank.")
    @NotNull(message = "BookedStatus cannot be Null.")
    private String bookedStatus;

    @NotNull(message = "ServiceName cannot be Null.")
    @NotBlank(message = "ServiceName cannot be Blank.")
    private String serviceName;
    @Min(value=100,message = "serviceCost cannot be less than 100.")
    private Double serviceCost;

    @Min(value=100,message = "TotalCost cannot be less than ServiceCost")
    private Double cartValue;

}
