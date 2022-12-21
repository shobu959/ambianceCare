package com.stackroute.authenticationservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @NotBlank(message = "user name can't be Blank")
    private String userName;
    @Id
    @Email(message = "your email address is invalid")
    private String userEmail;
    @Size(min = 2, max = 10, message = "user password size must be Min 2 to Max 10 character")
    private String userPassword;
    @Size(min = 2, max = 10, message = "user password size must be Min 2 to Max 10 character")
    private String confirmUserPassword;
    private String gender;
    @Pattern(regexp = "\\d{10}$", message = "you mobile number is invalid")
    private String mobileNo;
    @NotBlank(message = "user address can't be Blank")
    private String address;
    @NotNull(message = "your pinCode can't be Blank")
    private Integer pinCode;
    public String role;
}
