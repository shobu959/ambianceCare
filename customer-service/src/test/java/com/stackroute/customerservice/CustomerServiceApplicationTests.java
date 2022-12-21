package com.stackroute.customerservice;

import com.stackroute.customerservice.controller.CustomerController;
import com.stackroute.customerservice.entity.Services;
import com.stackroute.customerservice.exception.CustomerException;
import com.stackroute.customerservice.repository.ServicesRepository;
import com.stackroute.customerservice.serviceimpl.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceApplicationTests {

	@Mock
	ServicesRepository servicesRepository;

	@Mock
	@Autowired
	BookingServiceImpl bookingService;


	@Mock
	Services services;

	@Test
	void contextLoads() {
	}

	@Test
	public void getServiceByIdTest() throws CustomerException {
		when(servicesRepository.findServicesByServiceId("1")).thenReturn((new Services("1", "Men Hair Saloon", 550.0)));
		Services service = bookingService.fetchParticularService("1");
		assertEquals("Men Hair Saloon",service.getServiceName());
		assertEquals(550.0,service.getServiceCost());
	}

	@Test
	public void getServiceNameTest() throws CustomerException{
		Services service = bookingService.fetchParticularService("2");
		String serviceNameActual = "Electrician";
		String serviceNameExcpected = service.getServiceName();
		assertEquals(serviceNameActual,serviceNameExcpected);
	}
}
