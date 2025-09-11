package com.devsu.customer_service.domain.use_cases.customer.delete;

import com.devsu.customer_service.domain.exception.ResourceNotFoundException;
import com.devsu.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerDeleteUseCase {

	private final CustomerRepository customerRepository;

	public void execute(String customerId) throws ResourceNotFoundException {
		this.customerRepository.delete(customerId);
	}

}
