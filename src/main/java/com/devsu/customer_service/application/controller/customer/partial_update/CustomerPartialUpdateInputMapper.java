package com.devsu.customer_service.application.controller.customer.partial_update;

import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.application.controller.customer.update.CustomerUpdateRequest;
import com.devsu.customer_service.domain.use_cases.customer.partial_update.CustomerPartialUpdateInput;
import com.devsu.customer_service.domain.use_cases.customer.update.CustomerUpdateInput;
import jdk.dynalink.Operation;

import javax.swing.text.html.Option;
import java.util.Optional;

public class CustomerPartialUpdateInputMapper implements Mapper<CustomerPartialUpdateRequest, CustomerPartialUpdateInput> {

	@Override
	public CustomerPartialUpdateInput map(CustomerPartialUpdateRequest request) {
		CustomerPartialUpdateInput partialUpdateInput = new CustomerPartialUpdateInput();

		Optional.ofNullable(request.getClientId()).ifPresent(partialUpdateInput::setClientId);
		Optional.ofNullable(request.getName()).ifPresent(partialUpdateInput::setName);
		Optional.ofNullable(request.getGender()).ifPresent(partialUpdateInput::setGender);
		Optional.ofNullable(request.getAge()).ifPresent(partialUpdateInput::setAge);
		Optional.ofNullable(request.getAddress()).ifPresent(partialUpdateInput::setAddress);
		Optional.ofNullable(request.getPhone()).ifPresent(partialUpdateInput::setPhone);

		return partialUpdateInput;
	}

}
