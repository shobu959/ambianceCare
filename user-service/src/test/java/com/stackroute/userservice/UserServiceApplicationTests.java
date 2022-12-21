package com.stackroute.userservice;

import com.stackroute.userservice.dao.CustomerRepository;
import com.stackroute.userservice.dto.Customer;
import com.stackroute.userservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.security.RunAs;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
class UserServiceApplicationTests {
@Autowired
@MockBean
CustomerRepository customerRepo;
@InjectMocks
CustomerService customerService;

	@Test
	public void  testCreateNewCustomer() {
		Customer customer= new Customer();
		Mockito.when(customerRepo.save(customer)).thenReturn(customer);
		assertThat(customerService.createNewCustomer(customer)).isEqualTo(customer);

	}

}
