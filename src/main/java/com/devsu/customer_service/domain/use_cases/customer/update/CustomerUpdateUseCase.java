package com.devsu.customer_service.domain.use_cases.customer.update;

import com.devsu.customer_service.domain.exception.ResourceNotFoundException;
import com.devsu.customer_service.domain.exception.ValidationException;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerUpdateUseCase {

	private final CustomerRepository customerRepository;
	private final CustomerUpdateInputValidator customerUpdateInputValidator;

	public Customer execute(
	  String customerId,
	  CustomerUpdateInput customerUpdateInput
	) throws ResourceNotFoundException, ValidationException {
		this.customerUpdateInputValidator.validate(customerId, customerUpdateInput);

		return this.customerRepository.update(customerId, customerUpdateInput);
	}

}
