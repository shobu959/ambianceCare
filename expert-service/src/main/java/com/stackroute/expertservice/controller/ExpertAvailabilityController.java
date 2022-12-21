package com.stackroute.expertservice.controller;
import com.stackroute.expertservice.dto.ExpertAvailibilityRequest;
import com.stackroute.expertservice.entity.ExpertAvailability;
import com.stackroute.expertservice.entity.SlotStatus;
import com.stackroute.expertservice.exception.ExpertAvailabilityException;
import com.stackroute.expertservice.service.ExpertAvailabilityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1")
public class ExpertAvailabilityController {

    private static final Logger logger = LoggerFactory.getLogger(ExpertAvailabilityController.class);
    @Autowired
    private ExpertAvailabilityService expertservice;
    @PostMapping("expertAvailability")
    public ExpertAvailability addExpertAvailability(@Valid @RequestBody ExpertAvailibilityRequest expertavailability) throws  ExpertAvailabilityException {
        logger.info("addExpertAvailability()" , expertavailability  );
        ExpertAvailability response = this.expertservice.addExpertAvailability(expertavailability);
        logger.info("addExpertAvailability()", response);
        return response;
    }
    @GetMapping("expertsServices")
    public List<ExpertAvailability> findAllExperts() throws ExpertAvailabilityException {
        logger.info("findAllExperts()" );
        List<ExpertAvailability> response =  this.expertservice.findAllExperts();
        logger.info("findAllExperts()", response);
        return response;
    }
    @GetMapping("expert/{serviceId}")
    public ExpertAvailability getExpertByServiceID(@PathVariable("serviceId") String serviceId) throws ExpertAvailabilityException {
        return this.expertservice.getExpertByServiceID(serviceId);
    }
    @GetMapping("experts/{serviceName}")
    public List<ExpertAvailability> findExpertByServiceName(@PathVariable("serviceName") String serviceName) throws ExpertAvailabilityException {
        return this.expertservice.findExpertsByServiceName(serviceName);
    }
    @GetMapping("expertemail/{emailId}")
    public ExpertAvailability figureOutExpertByEmailId(@PathVariable("emailId") String emailId) throws ExpertAvailabilityException {
        return this.expertservice.getExpertByEmailID(emailId);
    }
    @PatchMapping("expertTodaySlotStatus/{serviceid}/{slotid}/{slotStatus}")
    @Transactional
    public ExpertAvailability updateTodaysSlotStatus(@PathVariable("serviceid") String serviceid, @PathVariable("slotid") String slotid, @PathVariable("slotStatus") SlotStatus slotStatus) throws ExpertAvailabilityException {
        return this.expertservice.updateExpertTodaysSlotStatus(serviceid,slotid,slotStatus);
    }

    @PatchMapping("expertTomorrowSlotStatus/{serviceid}/{slotid}/{slotStatus}")
    @Transactional
    public ExpertAvailability updateTomorrowSlotStatus(@PathVariable("serviceid") String serviceid, @PathVariable("slotid") String slotid, @PathVariable("slotStatus") SlotStatus slotStatus) throws ExpertAvailabilityException {
        return this.expertservice.updateExpertTomorrowSlotStatus(serviceid,slotid,slotStatus);
    }
    @PatchMapping("expertTodayTime/{serviceid}/{slotid}/{slottime}")
    @Transactional
    public ExpertAvailability changeTodaySlotTime(@PathVariable("serviceid") String serviceid, @PathVariable("slotid") String slotid, @PathVariable("slottime") String slottime) throws ExpertAvailabilityException {
        return this.expertservice.updateExpertTodaysTimeSlots(serviceid,slotid,slottime);
    }

    @PatchMapping("expertTomorrowTime/{serviceid}/{slotid}/{slottime}")
    @Transactional
    public ExpertAvailability changeTomorrowSlotTime(@PathVariable("serviceid") String serviceid, @PathVariable("slotid") String slotid, @PathVariable("slottime") String slottime) throws ExpertAvailabilityException {
        return this.expertservice.updateExpertTomorrowTimeSlots(serviceid,slotid,slottime);
    }
    @DeleteMapping("expertslot/{serviceid}/{slotid}")
    public ExpertAvailability deleteSlot(@PathVariable("serviceid") String serviceid, @PathVariable("slotid") String slotid){
        return this.expertservice.deleteExpertSlots(serviceid, slotid);
    }
    @DeleteMapping("deleteExpert/{serviceId}")
    public ExpertAvailability deleteExpert(@PathVariable("serviceId") String serviceId) throws ExpertAvailabilityException {
        return this.expertservice.deleteExpert(serviceId);
    }
}
