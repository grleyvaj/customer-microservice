package com.devsu.customer_service.domain.use_cases.customer.create;

import com.devsu.customer_service.domain.exception.ValidationException;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.repository.CustomerRepository;
import com.devsu.customer_service.domain.services.PasswordService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerCreateUseCase {

	private final CustomerRepository customerRepository;
	private final CustomerCreateInputValidator customerCreateInputValidator;
	private final PasswordService passwordEncoder;

	public Customer execute(CustomerCreateInput createInput) throws ValidationException {
		this.customerCreateInputValidator.validate(createInput);

		return this.customerRepository.create(
		  createInput.addEncryptedPassword(
			this.passwordEncoder.hash(createInput.getPassword())
		  )
		);

	}
}
