package com.devsu.customer_service.domain.use_cases.customer.list;

import com.devsu.customer_service.application.controller.pagination.response.PaginationFilters;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.pagination.PaginationModel;
import com.devsu.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerPaginatedListUseCase {

	private final CustomerRepository customerRepository;

	public PaginationModel<Customer> execute(PaginationFilters filters) {
		return this.customerRepository.getAll(filters);
	}
}
