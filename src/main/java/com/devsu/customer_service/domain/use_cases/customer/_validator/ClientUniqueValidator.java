package com.devsu.customer_service.domain.use_cases.customer._validator;

import com.devsu.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ClientUniqueValidator {

	public final CustomerRepository repository;

	public boolean isNotUnique(String customerId, String clientId) {
		Optional<String> foundCustomerId = this.repository.findByClient(clientId);
		return foundCustomerId.isPresent() && !foundCustomerId.get().equals(customerId);
	}

}
