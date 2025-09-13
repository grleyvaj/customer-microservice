package com.devsu.customer_service.infrastructure.rabbitmq.customer.create;

import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.domain.models.Customer;

public class CustomerCreatedMessageMapper implements Mapper<Customer, CustomerCreatedMessage> {

	@Override
	public CustomerCreatedMessage map(Customer event) {
		return new CustomerCreatedMessage(
		  event.getClientId(),
		  event.getName(),
		  event.getCreatedAt()
		);
	}

}
