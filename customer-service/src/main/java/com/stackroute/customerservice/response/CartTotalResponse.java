package com.stackroute.customerservice.response;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CartTotalResponse {

    @NotNull("Total amount for customer should not be null")
    @Min(value = 1,message = "Value can't be 0")
    Double totalAmount;
    @NotBlank(message = "customerEmail should not be empty")
    @NotNull("Email for customer should not be null")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String customerEmail;

    @NotNull("bookingId should not be null")
    String bookingId;

}
