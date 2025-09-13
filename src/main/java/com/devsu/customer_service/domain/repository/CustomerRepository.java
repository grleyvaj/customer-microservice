package com.devsu.customer_service.domain.repository;

import com.devsu.customer_service.application.controller.pagination.response.PaginationFilters;
import com.devsu.customer_service.domain.exception.ResourceNotFoundException;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.pagination.PaginationModel;
import com.devsu.customer_service.domain.use_cases.customer.create.CustomerCreateInput;
import com.devsu.customer_service.domain.use_cases.customer.partial_update.CustomerPartialUpdateInput;
import com.devsu.customer_service.domain.use_cases.customer.update.CustomerUpdateInput;

import java.util.Optional;

public interface CustomerRepository {

	Customer create(CustomerCreateInput customerCreateInput);

	boolean existsByClient(String clientId);

	Optional<Customer> findById(String customerId);

	Optional<String> findByClient(String clientId);

	Customer update(String customerId, CustomerUpdateInput customerUpdateInput) throws ResourceNotFoundException;

	Customer updatePartial(
	  String customerId,
	  CustomerPartialUpdateInput customerUpdateInput
	) throws ResourceNotFoundException;

	void updatePassword(String customerId, String newHashPassword) throws ResourceNotFoundException;

	PaginationModel<Customer> getAll(PaginationFilters filters);

	Customer delete(String customerId) throws ResourceNotFoundException;

}
