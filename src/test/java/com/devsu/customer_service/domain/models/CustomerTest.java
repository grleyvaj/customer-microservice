package com.devsu.customer_service.domain.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

	@Test
	void when_request_is_created_then_data_can_be_retrieved() {
		LocalDateTime dateTime = LocalDateTime.now();

		Customer customer = new Customer(
		  "::id::",
		  "::client::",
		  "::name::",
		  Gender.FEMALE,
		  35,
		  "::address::",
		  "+525562326895",
		  "::encrypted::",
		  Boolean.TRUE,
		  Boolean.FALSE,
		  dateTime,
		  dateTime
		);

		assertEquals("::id::", customer.getId());
		assertEquals("::client::", customer.getClientId());
		assertEquals("::name::", customer.getName());
		assertEquals(Gender.FEMALE, customer.getGender());
		assertEquals("::address::", customer.getAddress());
		assertEquals("+525562326895", customer.getPhone());
		assertEquals("::encrypted::", customer.getEncryptedPassword());
		assertTrue(customer.getIsActive());
		assertFalse(customer.getIsDeleted());
		assertEquals(dateTime, customer.getCreatedAt());
		assertEquals(dateTime, customer.getUpdatedAt());
	}

}