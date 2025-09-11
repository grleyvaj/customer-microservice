package com.devsu.customer_service.domain.use_cases.customer.detail;

import com.devsu.customer_service.domain.exception.ResourceNotFoundException;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerDetailUseCase {

	private final CustomerRepository customerRepository;

	public Customer execute(String customerId) throws ResourceNotFoundException {
		return this.customerRepository.findById(customerId).orElseThrow(
		  () -> new ResourceNotFoundException("Customer %s not found".formatted(customerId))
		);
	}

}
