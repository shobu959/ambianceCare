package rabbitMQDomain;

import com.stackroute.expertservice.entity.ExpertAvailability;

import java.util.List;

public class ExpertAvailabilityDTO {
    private String serviceId;
    private String expertEmail;
    private String serviceName;
    private Double price;
    private AvailabilityDTO availability;
    public ExpertAvailabilityDTO() {
        super();
    }

    public ExpertAvailabilityDTO(String serviceId, String expertEmail, String serviceName, Double price, AvailabilityDTO availability) {
        this.serviceId = serviceId;
        this.expertEmail = expertEmail;
        this.serviceName = serviceName;
        this.price = price;
        this.availability = availability;
    }



    public ExpertAvailabilityDTO(List<ExpertAvailability> expertList) {
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

    public AvailabilityDTO getAvailability() {
        return availability;
    }

    public void setAvailability(AvailabilityDTO availability) {
        this.availability = availability;
    }
}

