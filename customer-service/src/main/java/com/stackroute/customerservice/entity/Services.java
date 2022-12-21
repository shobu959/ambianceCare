package com.stackroute.customerservice.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "services")
public class Services {

    @Id
    private String serviceId;

    private String serviceName;

    private Double serviceCost;

    private String bookedDate;

    private String bookedStatus;

    private String bookedSlot;

    private String expertEmail;

    public Services(String i, String menHairSaloon, double v) {
    }
}
