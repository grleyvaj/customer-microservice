package com.devsu.customer_service.domain.models;

import com.devsu.customer_service.application.controller.customer.create.CustomerCreateRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomerCreateRequestTest {

	@Test
	void when_request_is_created_then_data_can_be_retrieved() {
		CustomerCreateRequest customer = new CustomerCreateRequest()
		  .setClientId("::client::")
		  .setName("::name::")
		  .setGender(Gender.MALE)
		  .setAge(36)
		  .setAddress("::address::")
		  .setPhone("+525562326895")
		  .setPassword("P@ssw0r.12345");

		assertEquals("::client::", customer.getClientId());
		assertEquals("::name::", customer.getName());
		assertEquals(Gender.MALE, customer.getGender());
		assertEquals(36, customer.getAge());
		assertEquals("::address::", customer.getAddress());
		assertEquals("+525562326895", customer.getPhone());
		assertEquals("P@ssw0r.12345", customer.getPassword());
	}

}