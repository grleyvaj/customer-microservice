package com.devsu.customer_service.application.controller.customer.create;

import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.domain.use_cases.customer.create.CustomerCreateInput;

public class CustomerCreateInputMapper implements Mapper<CustomerCreateRequest, CustomerCreateInput> {

	@Override
	public CustomerCreateInput map(CustomerCreateRequest request) {
		return new CustomerCreateInput(
		  request.getClientId(),
		  request.getName(),
		  request.getGender(),
		  request.getAge(),
		  request.getAddress(),
		  request.getPhone(),
		  request.getPassword()
		);
	}

}
