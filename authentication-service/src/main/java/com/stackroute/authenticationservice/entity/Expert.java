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

public class Expert {
    @Id
    @NotBlank(message = "email id  can't be Blank")
    @Email(message = "your email Id is invalid")
    private String userEmail;
    @NotBlank(message = "user name can't be Blank")
    private String userName;
    @Size(min=2, max=10,message = "user password size must be Min 2 to Max 10 character")
    private String userPassword;

    @Size(min=2, max=10,message = "user password size must be Min 2 to Max 4 character")
    private String confirmUserPassword;
    private  String gender;

    @Pattern(regexp = "\\d{10}$",message= "you mobile number is invalid")
    private  String mobileNo;
    @NotBlank ( message= "profession can't be blank character")
    private String profession;
    @NotBlank ( message= "skills can't be blank character")
    private String skills;
    @NotBlank(message = "address  can't be Blank")
    private String address;
    @NotNull(message = "your pinCode can't be Blank")
    private Integer pinCode;
    @NotNull(message = "Experienced  can't be Null")
    private String experienceMonths;
    private String role;
}
