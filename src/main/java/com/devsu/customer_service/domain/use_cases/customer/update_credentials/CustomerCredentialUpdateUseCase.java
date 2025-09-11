package com.devsu.customer_service.domain.use_cases.customer.update_credentials;

import com.devsu.customer_service.domain.exception.ResourceNotFoundException;
import com.devsu.customer_service.domain.exception.ValidationException;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.repository.CustomerRepository;
import com.devsu.customer_service.domain.services.PasswordService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CustomerCredentialUpdateUseCase {

	private final CustomerRepository customerRepository;
	private final PasswordService passwordEncoder;

	public void execute(
	  String customerId,
	  String oldPassword,
	  String newPassword
	) throws ResourceNotFoundException, ValidationException {
		Customer customer = this.customerRepository.findById(customerId)
		  .orElseThrow(() -> new ResourceNotFoundException("Customer %s not found".formatted(customerId)));

		this.validateCredential(oldPassword, customer.getEncryptedPassword());

		this.customerRepository.updatePassword(
		  customerId,
		  this.passwordEncoder.hash(newPassword)
		);
	}

	protected void validateCredential(
	  String oldPassword,
	  String hashedPassword
	) throws ValidationException {
		List<ValidationException.ValidationError> errors = new ArrayList<>();

		if(!this.passwordEncoder.verify(oldPassword, hashedPassword)) {
			errors.add(new ValidationException.ValidationError("old_password", "customer.oldPassword.invalid"));
		}

		if(!errors.isEmpty()) {
			throw new ValidationException(errors, "Invalid values");
		}
	}

}
