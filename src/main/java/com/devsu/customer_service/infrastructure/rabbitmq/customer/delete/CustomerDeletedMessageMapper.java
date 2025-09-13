package com.devsu.customer_service.infrastructure.rabbitmq.customer.delete;

import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.domain.models.Customer;

public class CustomerDeletedMessageMapper implements Mapper<Customer, CustomerDeletedMessage> {

	@Override
	public CustomerDeletedMessage map(Customer event) {
		return new CustomerDeletedMessage(
		  event.getClientId(),
		  event.getDeletedAt()
		);
	}

}
