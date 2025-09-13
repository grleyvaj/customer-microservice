package com.devsu.customer_service.infrastructure.rabbitmq.customer.update;

import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.domain.models.Customer;

public class CustomerUpdatedMessageMapper implements Mapper<Customer, CustomerUpdatedMessage> {

	@Override
	public CustomerUpdatedMessage map(Customer event) {
		return new CustomerUpdatedMessage(
		  event.getClientId(),
		  event.getName(),
		  event.getCreatedAt(),
		  event.getUpdatedAt()
		);
	}

}
