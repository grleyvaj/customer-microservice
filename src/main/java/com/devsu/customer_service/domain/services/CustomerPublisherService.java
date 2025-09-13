package com.devsu.customer_service.domain.services;

import com.devsu.customer_service.domain.exception.MessagingPublisherException;
import com.devsu.customer_service.domain.models.Customer;

public interface CustomerPublisherService {

	void publishCustomerCreated(Customer customerEvent) throws MessagingPublisherException;

	void publishCustomerUpdated(Customer updatedCustomer) throws MessagingPublisherException;

	void publishCustomerDeleted(Customer deletedCustomer) throws MessagingPublisherException;

}
