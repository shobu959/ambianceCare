package com.stackroute.expertservice.service;
import com.stackroute.expertservice.dao.ExpertAvailabilityRepo;
import com.stackroute.expertservice.dto.ExpertAvailibilityRequest;
import com.stackroute.expertservice.entity.ExpertAvailability;
import com.stackroute.expertservice.entity.SlotStatus;
import com.stackroute.expertservice.exception.ExpertAvailabilityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rabbitMQDomain.ExpertAvailabilityDTO;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpertAvailabilityServiceImpl implements ExpertAvailabilityService {
    @Autowired
    private ExpertAvailabilityRepo expertRepo;
    @Autowired
    private GeneratedServiceIdImpl autoServiceID;
    @Override
    public ExpertAvailability addExpertAvailability(ExpertAvailibilityRequest expertAvailability) throws ExpertAvailabilityException {
        ExpertAvailability availability = new ExpertAvailability();
        availability.setServiceId(autoServiceID.generateString());

        availability.setAvailability(expertAvailability.getAvailability());
        availability.setExpertEmail(expertAvailability.getExpertEmail());
        availability.setPrice(expertAvailability.getPrice());
        availability.setServiceName(expertAvailability.getServiceName());


//        if( availability.getAvailability().getTodaysSlot().getAvailableSLot().values().>1)
//        {
//            throw new ExpertAvailabilityException("One slot contains only one entry");
//        }

        return this.expertRepo.save(availability);
    }
    @Override
    public List<ExpertAvailability> findAllExperts() throws ExpertAvailabilityException {
        List<ExpertAvailability> expertList = this.expertRepo.findAll();
        ExpertAvailabilityDTO expertDTO = new ExpertAvailabilityDTO(expertList);


        if(expertList.isEmpty())
        {
            throw new ExpertAvailabilityException("No experts found");
        }
        return this.expertRepo.findAll();
    }
    @Override
    public ExpertAvailability getExpertByServiceID(String serviceId) throws ExpertAvailabilityException {
        ExpertAvailability optExpert =  this.expertRepo.findExpertByServiceId(serviceId);
        if(optExpert == null)
        {
            throw new ExpertAvailabilityException("Expert not present with service id");
        }
        return this.expertRepo.findExpertByServiceId(serviceId);
    }
    @Override
    public ExpertAvailability getExpertByEmailID(String emailId) throws ExpertAvailabilityException {
        ExpertAvailability optExpert = this.expertRepo.findExpertByEmailId(emailId);
        if(optExpert == null)
        {
            throw new ExpertAvailabilityException("Expert not present");
        }
        return this.expertRepo.findExpertByEmailId(emailId);
    }
    @Override
    public List<ExpertAvailability> findExpertsByServiceName(String serviceName) throws ExpertAvailabilityException {
        List<ExpertAvailability> optExpert = this.expertRepo.findExpertByServiceName(serviceName);
        if(optExpert.isEmpty())
        {
            throw new ExpertAvailabilityException("Service Not Found");
        }
        return this.expertRepo.findExpertByServiceName(serviceName);
    }
    @Override
    public ExpertAvailability updateExpertTodaysSlotStatus(String serviceid, String slotid, SlotStatus status) throws ExpertAvailabilityException {
        ExpertAvailability expert = this.expertRepo.findExpertByServiceId(serviceid);
        if(expert == null)
        {
            throw new ExpertAvailabilityException("No expert present with expert serviceID");
        }
        for (Map.Entry<String, Map<String, SlotStatus>> pair : expert.getAvailability().getTodaysSlot().getAvailableSLot().entrySet()) {
            if (pair.getKey().equals(slotid)) {
                for (Map.Entry<String, SlotStatus> p : pair.getValue().entrySet()) {
                    p.setValue(status);
                    Map<String, SlotStatus> mapFromSet = new HashMap<String, SlotStatus>();
                    // mapFromSet.put(p.getKey(),p.getValue());
                    mapFromSet.put(p.getKey(), status);
                }
            }
        }
        return this.expertRepo.save(expert);
    }

    @Override
    public ExpertAvailability updateExpertTomorrowSlotStatus(String serviceid, String slotid, SlotStatus status) throws ExpertAvailabilityException {
        ExpertAvailability expert = this.expertRepo.findExpertByServiceId(serviceid);
        if(expert == null)
        {
            throw new ExpertAvailabilityException("No expert present with expert serviceID");
        }
        for (Map.Entry<String, Map<String, SlotStatus>> pair : expert.getAvailability().getTomorrowSlot().getAvailableSLot().entrySet()) {
            if (pair.getKey().equals(slotid)) {
                for (Map.Entry<String, SlotStatus> p : pair.getValue().entrySet()) {
                    p.setValue(status);
                    Map<String, SlotStatus> mapFromSet = new HashMap<String, SlotStatus>();
                    // mapFromSet.put(p.getKey(),p.getValue());
                    mapFromSet.put(p.getKey(), status);
                }
            }
        }
        return this.expertRepo.save(expert);
    }
    @Override
    public ExpertAvailability updateExpertTodaysTimeSlots(String serviceId, String slotId, String time) throws ExpertAvailabilityException {
        ExpertAvailability expert = this.expertRepo.findExpertByServiceId(serviceId);
        if(expert == null)
        {
            throw new ExpertAvailabilityException("No expert present with expert serviceID");
        }
        for (Map.Entry<String, Map<String, SlotStatus>> pair : expert.getAvailability().getTodaysSlot().getAvailableSLot().entrySet()) {
            if (pair.getKey().equals(slotId)) {
                for (Map.Entry<String, SlotStatus> p : pair.getValue().entrySet()) {
                    //creating the duplicate slot
                    Map<String, SlotStatus> mapFromSet = new HashMap<String, SlotStatus>();
                    mapFromSet.put(p.getKey(), p.getValue());
                    mapFromSet.remove(p.getKey());
                    mapFromSet.put(time, p.getValue());
                    //removing value from outer entry set
                    pair.getValue().entrySet().remove(p);
                    //putting the new map as value in outer set
                    pair.setValue(mapFromSet);
                }
            }
        }
        return this.expertRepo.save(expert);
    }

    @Override
    public ExpertAvailability updateExpertTomorrowTimeSlots(String serviceId, String slotId, String time) throws ExpertAvailabilityException {
        ExpertAvailability expert = this.expertRepo.findExpertByServiceId(serviceId);
        if(expert == null)
        {
            throw new ExpertAvailabilityException("No expert present with expert serviceID");
        }
        for (Map.Entry<String, Map<String, SlotStatus>> pair : expert.getAvailability().getTomorrowSlot().getAvailableSLot().entrySet()) {
            if (pair.getKey().equals(slotId)) {
                for (Map.Entry<String, SlotStatus> p : pair.getValue().entrySet()) {
                    //creating the duplicate slot
                    Map<String, SlotStatus> mapFromSet = new HashMap<String, SlotStatus>();
                    mapFromSet.put(p.getKey(), p.getValue());
                    mapFromSet.remove(p.getKey());
                    mapFromSet.put(time, p.getValue());
                    //removing value from outer entry set
                    pair.getValue().entrySet().remove(p);
                    //putting the new map as value in outer set
                    pair.setValue(mapFromSet);
                }
            }
        }
        return this.expertRepo.save(expert);
    }
    @Override
    public ExpertAvailability deleteExpertSlots(String serviceId, String slotId) {
        ExpertAvailability expert = this.expertRepo.findExpertByServiceId(serviceId);
        for (Map.Entry<String, Map<String, SlotStatus>> pair : expert.getAvailability().getTodaysSlot().getAvailableSLot().entrySet()) {
            if (pair.getKey().equals(slotId)) {
                for (Map.Entry<String, SlotStatus> p : pair.getValue().entrySet()) {
                    pair.getValue().entrySet().remove(p);
                }
            }
        }

         return  expert;
        }
    @Override
    public ExpertAvailability deleteExpert(String serviceID) throws ExpertAvailabilityException {
        ExpertAvailability optExpert =  this.expertRepo.findExpertByServiceId(serviceID);
        if(optExpert == null)
        {
            throw new ExpertAvailabilityException("Expert not present with service id");
        }
        return this.expertRepo.deleteExpertBYserviceId(serviceID);
    }
}

