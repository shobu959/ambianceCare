package com.stackroute.emailservice;

import com.stackroute.emailservice.entity.EmailRequest;
import com.stackroute.emailservice.entity.EmailResponse;
import com.stackroute.emailservice.service.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmailServiceApplicationTests {

	@Autowired
	EmailServiceImpl emailService;


	@Test
	public void sendBookingMailTest(){
		EmailRequest request = new EmailRequest();
		request.setBookingId("1675erujdd");
		request.setCustomerEmail("a@gmail.com");
		request.setExpertEmail("b@gmail.com");
		request.setBookedStatus("Booked");
		request.setBookingSlot("11-2");
		request.setCustomerAddress("123,west mumbai");
		request.setPincode("123456");
		request.setServiceName("Saloon");
		request.setServiceCost(200.0);
		request.setCartValue(200.0);
		request.setBookedDate(LocalDate.of(2022,12,20));
		EmailResponse response = new EmailResponse("mail sent to : "+request.getExpertEmail()+" and "+request.getCustomerEmail(),true);


		assertEquals(response.getStatus(),emailService.sendBookingMail(request).getStatus());
		assertEquals(response.getMessage(),emailService.sendBookingMail(request).getMessage());

	}

	@Test
	public void sendCancellationMailTest(){
		EmailRequest request = new EmailRequest();
		request.setBookingId("1675erujdd");
		request.setCustomerEmail("a@gmail.com");
		request.setExpertEmail("b@gmail.com");
		request.setBookedStatus("Cancelled");
		request.setBookingSlot("11-2");
		request.setCustomerAddress("123,west mumbai");
		request.setPincode("123456");
		request.setServiceName("Saloon");
		request.setServiceCost(200.0);
		request.setCartValue(200.0);
		request.setBookedDate(LocalDate.of(2022,12,20));
		EmailResponse response = new EmailResponse("mail sent to : "+request.getExpertEmail()+" and "+request.getCustomerEmail(),true);


		assertEquals(response.getStatus(),emailService.sendBookingMail(request).getStatus());
		assertEquals(response.getMessage(),emailService.sendBookingMail(request).getMessage());

	}

	@Test
	public void sendRescheduleMailTest(){
		EmailRequest request = new EmailRequest();
		request.setBookingId("1675erujdd");
		request.setCustomerEmail("a@gmail.com");
		request.setExpertEmail("b@gmail.com");
		request.setBookedStatus("Rescheduled");
		request.setBookingSlot("11-2");
		request.setCustomerAddress("123,west mumbai");
		request.setPincode("123456");
		request.setServiceName("Saloon");
		request.setServiceCost(200.0);
		request.setCartValue(200.0);
		request.setBookedDate(LocalDate.of(2022,12,20));
		EmailResponse response = new EmailResponse("mail sent to : "+request.getExpertEmail()+" and "+request.getCustomerEmail(),true);


		assertEquals(response.getStatus(),emailService.sendBookingMail(request).getStatus());
		assertEquals(response.getMessage(),emailService.sendBookingMail(request).getMessage());

	}

}
