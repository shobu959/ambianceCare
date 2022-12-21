package com.stackroute.userservice.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Document
public class Expert {
    @NotBlank(message = "user name can't be Blank")
    private String userName;
    @Id
    @NotBlank(message = "email id  can't be Blank")
    @Email(message = "your email Id is invalid")
    private String userEmail;
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


    public Expert() {
    }

    public Expert( String userName, String userEmail, String userPassword, String confirmUserPassword, String gender, String mobileNo, String profession, String skills, String address, Integer pinCode, String experienceMonths, String role) {

        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.confirmUserPassword = confirmUserPassword;
        this.gender = gender;
        this.mobileNo = mobileNo;
        this.profession = profession;
        this.skills = skills;
        this.address = address;
        this.pinCode = pinCode;
        this.experienceMonths = experienceMonths;
        this.role = role;
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

    public void setUserPassword(String userPassword) {
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
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

    public String getExperienceMonths() {
        return experienceMonths;
    }

    public void setExperienceMonths(String experienceMonths) {
        this.experienceMonths = experienceMonths;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
