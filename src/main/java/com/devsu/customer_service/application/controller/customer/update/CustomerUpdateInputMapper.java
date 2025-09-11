package com.devsu.customer_service.application.controller.customer.update;

import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.domain.use_cases.customer.update.CustomerUpdateInput;

public class CustomerUpdateInputMapper implements Mapper<CustomerUpdateRequest, CustomerUpdateInput> {

	@Override
	public CustomerUpdateInput map(CustomerUpdateRequest request) {
		return new CustomerUpdateInput(
		  request.getClientId(),
		  request.getName(),
		  request.getGender(),
		  request.getAge(),
		  request.getAddress(),
		  request.getPhone()
		);
	}

}
