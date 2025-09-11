package com.devsu.customer_service.domain.use_cases.customer.create;

import com.devsu.customer_service.domain.exception.ValidationException;
import com.devsu.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CustomerCreateInputValidator {

	public final CustomerRepository repository;

	public void validate(CustomerCreateInput createInput) throws ValidationException {
		List<ValidationException.ValidationError> errors = new ArrayList<>(validateUniqueClient(createInput.getClientId()));

		if (!errors.isEmpty()) {
			throw new ValidationException(errors, "Invalid values");
		}
	}

	private List<ValidationException.ValidationError> validateUniqueClient(String clientId) {
		List<ValidationException.ValidationError> errors = new ArrayList<>();

		boolean clientAlreadyExist = this.repository.existsByClient(clientId);

		if (clientAlreadyExist) {
			errors.add(new ValidationException.ValidationError("client_id", "customer.clientId.unique"));
		}

		return errors;
	}

}
