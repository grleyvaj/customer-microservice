package com.devsu.customer_service.domain.use_cases.customer.update;

import com.devsu.customer_service.domain.event.CustomerUpdatedDomainEvent;
import com.devsu.customer_service.domain.exception.ResourceNotFoundException;
import com.devsu.customer_service.domain.exception.ValidationException;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CustomerUpdateUseCase {

	private final CustomerRepository customerRepository;
	private final CustomerUpdateInputValidator customerUpdateInputValidator;
	private final ApplicationEventPublisher applicationEventPublisher;

	@Transactional
	public Customer execute(
	  String customerId,
	  CustomerUpdateInput customerUpdateInput
	) throws ResourceNotFoundException, ValidationException {
		this.customerUpdateInputValidator.validate(customerId, customerUpdateInput);

		Customer customer = this.customerRepository.update(customerId, customerUpdateInput);

		publisherUpdatedCustomerEvent(customer);

		return customer;
	}

	private void publisherUpdatedCustomerEvent(Customer customer) {
		this.applicationEventPublisher.publishEvent(
		  new CustomerUpdatedDomainEvent(customer.getClientId(), customer)
		);
	}

}
