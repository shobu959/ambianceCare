package com.stackroute.expertservice.service;

import com.stackroute.expertservice.dto.ExpertAvailibilityRequest;
import com.stackroute.expertservice.entity.ExpertAvailability;
import com.stackroute.expertservice.entity.SlotStatus;
import com.stackroute.expertservice.exception.ExpertAvailabilityException;

import java.text.ParseException;
import java.util.List;

public interface ExpertAvailabilityService {
    ExpertAvailability addExpertAvailability(ExpertAvailibilityRequest expertAvailability) throws ExpertAvailabilityException;
    List<ExpertAvailability> findAllExperts() throws ExpertAvailabilityException;
    ExpertAvailability getExpertByServiceID(String serviceId) throws ExpertAvailabilityException;
    ExpertAvailability getExpertByEmailID(String emailId) throws ExpertAvailabilityException;
    List<ExpertAvailability> findExpertsByServiceName(String serviceName) throws ExpertAvailabilityException;
    ExpertAvailability updateExpertTodaysSlotStatus(String serviceId, String SlotID, SlotStatus status) throws ExpertAvailabilityException;
    ExpertAvailability updateExpertTomorrowSlotStatus(String serviceId, String SlotID, SlotStatus status) throws ExpertAvailabilityException;
    ExpertAvailability updateExpertTodaysTimeSlots(String serviceId, String SlotId, String time) throws ExpertAvailabilityException;
    ExpertAvailability updateExpertTomorrowTimeSlots(String serviceId, String SlotId, String time) throws ExpertAvailabilityException;
    ExpertAvailability deleteExpertSlots(String serviceId, String SlotId);
    ExpertAvailability deleteExpert(String ServiceID) throws ExpertAvailabilityException;
}
