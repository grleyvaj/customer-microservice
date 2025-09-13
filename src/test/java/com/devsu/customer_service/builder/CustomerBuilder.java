package com.devsu.customer_service.builder;

import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.models.Gender;

import java.time.LocalDateTime;

public class CustomerBuilder extends Builder<Customer> {

	@Override
	public Customer build(int index) {
		return new Customer(
		  String.valueOf(index),
		  "::client-" + index + "::",
		  "::name-" + index + "::",
		  Gender.MALE,
		  index + 30,
		  "::address-" + index + "::",
		  "+556262626" + index,
		  "P@ssw0r." + index * 50,
		  Boolean.TRUE,
		  Boolean.FALSE,
		  LocalDateTime.now(),
		  LocalDateTime.now()
		);
	}

}
