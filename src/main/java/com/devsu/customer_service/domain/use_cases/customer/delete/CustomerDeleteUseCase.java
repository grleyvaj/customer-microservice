package com.devsu.customer_service.domain.use_cases.customer.delete;

import com.devsu.customer_service.domain.event.CustomerDeletedDomainEvent;
import com.devsu.customer_service.domain.exception.ResourceNotFoundException;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CustomerDeleteUseCase {

	private final CustomerRepository customerRepository;
	private final ApplicationEventPublisher applicationEventPublisher;

	@Transactional
	public void execute(String customerId) throws ResourceNotFoundException {
		Customer deletedCustomer = this.customerRepository.delete(customerId);

		publisherDeletedCustomerEvent(deletedCustomer);
	}

	private void publisherDeletedCustomerEvent(Customer customer) {
		this.applicationEventPublisher.publishEvent(
		  new CustomerDeletedDomainEvent(customer.getClientId(), customer)
		);
	}
}
