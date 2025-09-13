package com.devsu.customer_service.domain.event;

import com.devsu.customer_service.domain.models.Customer;
import lombok.Data;

@Data
public class CustomerUpdatedDomainEvent {

	private final String clientId;
	private final Customer customer;

}