package com.devsu.customer_service.builder;

import com.devsu.customer_service.domain.models.Gender;
import com.devsu.customer_service.domain.use_cases.customer.create.CustomerCreateInput;

public class CustomerCreateInputBuilder extends Builder<CustomerCreateInput> {

	@Override
	public CustomerCreateInput build(int index) {
		return new CustomerCreateInput(
		  "::client-" + index + "::",
		  "::name-" + index + "::",
		  Gender.MALE,
		  index + 30,
		  "::address-" + index + "::",
		  "+556262626" + index,
		  "P@ssw0r." + index * 50
		);
	}

}
