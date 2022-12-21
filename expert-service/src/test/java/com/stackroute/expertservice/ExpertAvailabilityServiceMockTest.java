package com.stackroute.expertservice;

import com.stackroute.expertservice.dao.ExpertAvailabilityRepo;
import com.stackroute.expertservice.dto.ExpertAvailibilityRequest;
import com.stackroute.expertservice.entity.Availability;
import com.stackroute.expertservice.entity.ExpertAvailability;
import com.stackroute.expertservice.entity.SlotStatus;
import com.stackroute.expertservice.entity.Slots;
import com.stackroute.expertservice.exception.ExpertAvailabilityException;
import com.stackroute.expertservice.service.ExpertAvailabilityService;
import com.stackroute.expertservice.service.ExpertAvailabilityServiceImpl;
import com.stackroute.expertservice.service.GeneratedServiceIdImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest

public class ExpertAvailabilityServiceMockTest {

    @Mock
    private ExpertAvailabilityRepo expertRepo;

    @Autowired
    private GeneratedServiceIdImpl autoServiceID;

    @InjectMocks
    private ExpertAvailabilityServiceImpl expertservice;

    @Test
    public void getExpertByServiceId() throws ExpertAvailabilityException {
        Availability availability = new Availability("18-12-2022", new Slots(), "19-12-2022", new Slots());
        ExpertAvailability response = new ExpertAvailability("abcd","sumit@gmail.com", "painter", 399.0,availability );
        Mockito.when(expertRepo.findExpertByServiceId("abcd")).thenReturn(response);
        assertEquals("sumit@gmail.com", expertservice.getExpertByServiceID("abcd").getExpertEmail());
    }

    @Test
    public void getExpertByemailID() throws ExpertAvailabilityException {
        Availability availability = new Availability("18-12-2022", new Slots(), "19-12-2022", new Slots());
        ExpertAvailability response = new ExpertAvailability("abcd","sumit@gmail.com", "painter", 399.0,availability );
        Mockito.when(expertRepo.findExpertByEmailId("sumit@gmail.com")).thenReturn(response);
        assertEquals("sumit@gmail.com", expertservice.getExpertByEmailID("sumit@gmail.com").getExpertEmail());

    }
    @Test
    public void getExpertbyServiceName() throws ExpertAvailabilityException {
        Availability availability = new Availability("18-12-2022", new Slots(), "19-12-2022", new Slots());
        ExpertAvailability response = new ExpertAvailability("abcd","sumit@gmail.com", "painter", 399.0,availability );
        List<ExpertAvailability> responselist = new ArrayList<>();
        responselist.add(response);
        Mockito.when(expertRepo.findExpertByServiceName("painter")).thenReturn((responselist));
        assertEquals(1, expertservice.findExpertsByServiceName("painter").size());
    }

//    @Test
//    public void deleteExpertByserviceId() throws ExpertAvailabilityException {
//        Availability availability = new Availability("18-12-2022", new Slots(), "19-12-2022", new Slots());
//        ExpertAvailability response = new ExpertAvailability("abcd","sumit@gmail.com", "painter", 399.0,availability );
//        //Mockito.when(expertRepo.deleteExpertBYserviceId("abcd")).thenReturn((response));
//        //assertEquals("sumit@gmail.com", expertservice.deleteExpert("abcd").getExpertEmail());
//        expertservice.deleteExpert(response.getServiceId());
//        Mockito.verify(expertRepo, Mockito.times(1)).deleteExpertBYserviceId(response.getServiceId());
//
//
//    }

}
