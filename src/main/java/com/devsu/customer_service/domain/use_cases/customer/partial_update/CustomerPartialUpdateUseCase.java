package com.devsu.customer_service.domain.use_cases.customer.partial_update;

import com.devsu.customer_service.domain.exception.ResourceNotFoundException;
import com.devsu.customer_service.domain.exception.ValidationException;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerPartialUpdateUseCase {

	private final CustomerRepository customerRepository;
	private final CustomerPartialUpdateInputValidator customerUpdateInputValidator;

	public Customer execute(
	  String customerId,
	  CustomerPartialUpdateInput customerUpdateInput
	) throws ResourceNotFoundException, ValidationException {
		this.customerUpdateInputValidator.validate(customerId, customerUpdateInput);

		return this.customerRepository.updatePartial(customerId, customerUpdateInput);
	}

}
