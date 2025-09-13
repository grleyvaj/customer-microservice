package com.devsu.customer_service.domain.use_cases.customer.create;

import com.devsu.customer_service.domain.exception.ValidationException;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.repository.CustomerRepository;
import com.devsu.customer_service.domain.services.PasswordService;
import com.devsu.customer_service.domain.event.CustomerCreatedDomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
public class CustomerCreateUseCase {

	private final CustomerRepository customerRepository;
	private final CustomerCreateInputValidator customerCreateInputValidator;
	private final PasswordService passwordEncoder;
	private final ApplicationEventPublisher applicationEventPublisher;

	@Transactional
	public Customer execute(CustomerCreateInput createInput) throws ValidationException {
		this.customerCreateInputValidator.validate(createInput);

		Customer customer = this.customerRepository.create(
		  createInput.addEncryptedPassword(
			this.passwordEncoder.hash(createInput.getPassword())
		  )
		);

		publisherCreatedCustomerEvent(customer);

		return customer;
	}

	private void publisherCreatedCustomerEvent(Customer customer) {
		this.applicationEventPublisher.publishEvent(
		  new CustomerCreatedDomainEvent(customer.getClientId(), customer)
		);
	}

}
