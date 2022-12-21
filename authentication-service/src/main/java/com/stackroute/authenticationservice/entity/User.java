package com.stackroute.authenticationservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User {

    @Id
    @NotNull(message = "Useremail Can't Be Null")
    private String userEmail;
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$", message = "password must 8 characters, 2 UpperCase, 1 Special Character, 2 Numbers & 3 LowerCase")
    private String password;

}
