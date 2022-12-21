package com.stackroute.customerservice.request;

import com.stackroute.customerservice.entity.Availability;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class BookingRequest {

    private Availability availability;

    @NotBlank(message = "Service Id should not be empty")
    @NotNull("Service Id for expert service should not be null")
    private String serviceId;

    @NotBlank(message = "Service Name should not be empty")
    @NotNull("Service Name for expert service should not be null")
    private String serviceName;

    @NotNull("Service cost for expert service should not be null")
    private Double serviceCost;

    @NotBlank(message = "bookedDate should not be empty")
    @NotNull("Booked Date for customer should not be null")
    private String bookedDate;

    @NotBlank(message = "bookedStatus should not be empty")
    @NotNull("Booked Status for customer should not be null")
    private String bookedStatus;

    @NotBlank(message = "bookedSlot should not be empty")
    @NotNull("bookedSlot for customer should not be null")
    private String bookedSlot;

    @NotBlank(message = "expertEmail should not be empty")
    @NotNull("Email for export should not be null")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String expertEmail;

    @NotBlank(message = "customerEmail should not be empty")
    @NotNull("Email for customer should not be null")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String customerEmail;

    @NotBlank(message = "customerAddress should not be empty")
    @NotNull("Customer Address should not be null")
    private String customerAddress;

    @NotBlank(message = "Pin code should not be empty")
    @NotNull("Pin code should not be null")
    private String pincode;

    @NotNull(" Cart Value should not be null")
    @Min(value = 1,message = "Cart value cannot be 0")
    private Integer cartValue;
}
