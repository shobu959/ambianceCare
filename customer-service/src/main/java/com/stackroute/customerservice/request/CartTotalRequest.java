package com.stackroute.customerservice.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartTotalRequest {

    @NotNull("bookingId should not be null")
    String bookingId;

    @NotBlank(message = "customerEmail should not be empty")
    @NotNull("Email for customer should not be null")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String customerEmail;

}
