package com.stackroute.expertservice.dto;
import com.stackroute.expertservice.entity.Availability;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class ExpertAvailibilityRequest {
    @NotBlank(message = "customerEmail should not be empty")
    @NotNull(message ="Email for customer should not be null")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String expertEmail;
    @NotBlank(message = "Please provide ServiceName")
    private String serviceName;
    @NotNull(message = "Please provide a price")
    @Min(value = 99, message = "minimum price should be greater than 99")
    private Double price;
    private Availability availability;
    public ExpertAvailibilityRequest() {
        super();
    }
    public ExpertAvailibilityRequest(String expertEmail, String serviceName, Double price, Availability availability) {
        this.expertEmail = expertEmail;
        this.serviceName = serviceName;
        this.price = price;
        this.availability = availability;
    }


    public String getExpertEmail() {
        return expertEmail;
    }
    public void setExpertEmail(String expertEmail) {
        this.expertEmail = expertEmail;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Availability getAvailability() {
        return availability;
    }
    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
}
