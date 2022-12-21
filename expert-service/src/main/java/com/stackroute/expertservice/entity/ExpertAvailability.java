package com.stackroute.expertservice.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "ExpertAvailability")
public class ExpertAvailability {
    @Id
    private String serviceId;
    private String expertEmail;
    private String serviceName;
    private Double price;
    private Availability availability;
    public ExpertAvailability() {
        super();
    }
    public ExpertAvailability(String serviceId, String expertEmail, String serviceName, Double price, Availability availability) {
        this.serviceId = serviceId;
        this.expertEmail = expertEmail;
        this.serviceName = serviceName;
        this.price = price;
        this.availability = availability;
    }
    public String getServiceId() {
        return serviceId;
    }
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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

