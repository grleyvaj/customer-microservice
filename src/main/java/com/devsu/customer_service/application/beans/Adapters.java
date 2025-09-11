package com.devsu.customer_service.application.beans;

import com.devsu.customer_service.application.contract.ListMapper;
import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.application.controller.customer.create.CustomerCreateInputMapper;
import com.devsu.customer_service.application.controller.customer.create.CustomerCreateRequest;
import com.devsu.customer_service.application.controller.customer.detail.CustomerDetailResponse;
import com.devsu.customer_service.application.controller.customer.detail.CustomerDetailResponseMapper;
import com.devsu.customer_service.application.controller.customer.list.CustomerSummaryResponse;
import com.devsu.customer_service.application.controller.customer.list.CustomerSummaryResponseMapper;
import com.devsu.customer_service.application.controller.customer.partial_update.CustomerPartialUpdateInputMapper;
import com.devsu.customer_service.application.controller.customer.partial_update.CustomerPartialUpdateRequest;
import com.devsu.customer_service.application.controller.customer.update.CustomerUpdateInputMapper;
import com.devsu.customer_service.application.controller.customer.update.CustomerUpdateRequest;
import com.devsu.customer_service.application.controller.pagination.response.PageResponse;
import com.devsu.customer_service.application.controller.pagination.response.PageResponseMapper;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.pagination.Pagination;
import com.devsu.customer_service.domain.use_cases.customer.create.CustomerCreateInput;
import com.devsu.customer_service.domain.use_cases.customer.partial_update.CustomerPartialUpdateInput;
import com.devsu.customer_service.domain.use_cases.customer.update.CustomerUpdateInput;
import com.devsu.customer_service.infrastructure.entity.CustomerEntity;
import com.devsu.customer_service.infrastructure.entity.CustomerEntityMapper;
import com.devsu.customer_service.infrastructure.entity.CustomerEntityUpdater;
import com.devsu.customer_service.infrastructure.entity.CustomerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Adapters {

	@Bean
	public Mapper<CustomerCreateRequest, CustomerCreateInput> customerCreateInputMapper() {
		return new CustomerCreateInputMapper();
	}

	@Bean
	public Mapper<CustomerCreateInput, CustomerEntity> customerEntityMapper() {
		return new CustomerEntityMapper();
	}

	@Bean
	public Mapper<CustomerEntity, Customer> customerMapper() {
		return new CustomerMapper();
	}

	@Bean
	public Mapper<Customer, CustomerDetailResponse> customerDetailResponseMapper() {
		return new CustomerDetailResponseMapper();
	}

	@Bean
	public Mapper<CustomerUpdateRequest, CustomerUpdateInput> customerUpdateInputMapper() {
		return new CustomerUpdateInputMapper();
	}

	@Bean
	public CustomerEntityUpdater customerEntityUpdater() {
		return new CustomerEntityUpdater();
	}

	@Bean
	public Mapper<CustomerPartialUpdateRequest, CustomerPartialUpdateInput> customerPartialUpdateInputMapper() {
		return new CustomerPartialUpdateInputMapper();
	}

	@Bean
	public Mapper<Customer, CustomerSummaryResponse> customerSummaryResponseMapper() {
		return new CustomerSummaryResponseMapper();
	}

	@Bean
	public ListMapper<Customer, CustomerSummaryResponse> customerSummaryResponseListMapper(
	  Mapper<Customer, CustomerSummaryResponse> customerSummaryResponseMapper
	) {
		return new ListMapper<>(customerSummaryResponseMapper);
	}

	@Bean
	public Mapper<Pagination, PageResponse> pageResponseMapper() {
		return new PageResponseMapper();
	}

}
