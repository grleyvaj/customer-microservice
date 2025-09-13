package com.devsu.customer_service.builder;

import com.devsu.customer_service.application.controller.customer.detail.CustomerDetailResponse;
import com.devsu.customer_service.domain.models.Gender;

public class CustomerDetailResponseBuilder extends Builder<CustomerDetailResponse> {

	@Override
	public CustomerDetailResponse build(int index) {
		return new CustomerDetailResponse()
		  .setClientId(String.valueOf(index))
		  .setClientId("::client-" + index + "::")
		  .setName("::name-" + index + "::")
		  .setGender(Gender.MALE.getName())
		  .setAge(index)
		  .setAddress("::address-" + index + "::")
		  .setPhone("+556262626" + index)
		  .setEncryptedPassword("P@ssw0r." + index * 50)
		  .setState(Boolean.TRUE);
	}

}
