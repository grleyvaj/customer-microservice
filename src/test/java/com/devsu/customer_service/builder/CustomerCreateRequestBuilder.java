package com.devsu.customer_service.builder;

import com.devsu.customer_service.application.controller.customer.create.CustomerCreateRequest;
import com.devsu.customer_service.domain.models.Gender;

public class CustomerCreateRequestBuilder extends Builder<CustomerCreateRequest> {

	@Override
	public CustomerCreateRequest build(int index) {
		return new CustomerCreateRequest()
		  .setClientId("::client-" + index + "::")
		  .setName("::name-" + index + "::")
		  .setGender(Gender.MALE)
		  .setAge(index)
		  .setAddress("::address-" + index + "::")
		  .setPhone("+556262626" + index)
		  .setPassword("P@ssw0r." + index * 50);
	}

}
