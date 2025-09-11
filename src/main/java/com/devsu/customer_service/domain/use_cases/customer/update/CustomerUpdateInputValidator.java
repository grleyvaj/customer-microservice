package com.devsu.customer_service.domain.use_cases.customer.update;

import com.devsu.customer_service.domain.exception.ValidationException;
import com.devsu.customer_service.domain.repository.CustomerRepository;
import com.devsu.customer_service.domain.use_cases.customer._validator.ClientUniqueValidator;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CustomerUpdateInputValidator {

	public final CustomerRepository repository;
	public final ClientUniqueValidator clientUniqueValidator;

	public void validate(String customerId, CustomerUpdateInput updateInput) throws ValidationException {
		List<ValidationException.ValidationError> errors = new ArrayList<>();

		if(this.clientUniqueValidator.isNotUnique(customerId, updateInput.getClientId())) {
			errors.add(new ValidationException.ValidationError("client_id", "customer.clientId.unique"));
		}

		if(!errors.isEmpty()) {
			throw new ValidationException(errors, "Invalid values");
		}
	}

}
