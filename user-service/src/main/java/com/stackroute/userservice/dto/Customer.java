package com.stackroute.userservice.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;


@Document
public class Customer {

   @NotBlank(message = "user name can't be Blank")
    private String userName;
   @Id
  @Email(message = "your email address is invalid")
   private String userEmail;
    @Size(min=2, max=10,message = "user password size must be Min 2 to Max 10 character")
    private String userPassword;
    @Size(min=2, max=10,message = "user password size must be Min 2 to Max 10 character")
    private String confirmUserPassword;

    private  String gender;
   @Pattern(regexp = "\\d{10}$",message= "you mobile number is invalid")
    private  String mobileNo;
    @NotBlank(message = "user address can't be Blank")
    private String address;
    @NotNull(message = "your pinCode can't be Blank")
    private Integer pinCode;
     public String  role;


    public Customer() {
    }

    public Customer( String userName, String userEmail, String userPassword, String confirmUserPassword, String gender, String mobileNo, String address, Integer pinCode) {

        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.confirmUserPassword = confirmUserPassword;
        this.gender = gender;
        this.mobileNo = mobileNo;
        this.address = address;
        this.pinCode = pinCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {

        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword() {
        this.userPassword = userPassword;
    }

    public String getConfirmUserPassword() {
        return confirmUserPassword;
    }

    public void setConfirmUserPassword(String confirmUserPassword) {
        this.confirmUserPassword = confirmUserPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }
}