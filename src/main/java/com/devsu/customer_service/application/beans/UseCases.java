package com.devsu.customer_service.application.beans;

import com.devsu.customer_service.domain.repository.CustomerRepository;
import com.devsu.customer_service.domain.services.PasswordService;
import com.devsu.customer_service.domain.use_cases.customer.create.CustomerCreateInputValidator;
import com.devsu.customer_service.domain.use_cases.customer.create.CustomerCreateUseCase;
import com.devsu.customer_service.domain.use_cases.customer.delete.CustomerDeleteUseCase;
import com.devsu.customer_service.domain.use_cases.customer.detail.CustomerDetailUseCase;
import com.devsu.customer_service.domain.use_cases.customer.list.CustomerPaginatedListUseCase;
import com.devsu.customer_service.domain.use_cases.customer.partial_update.CustomerPartialUpdateInputValidator;
import com.devsu.customer_service.domain.use_cases.customer.partial_update.CustomerPartialUpdateUseCase;
import com.devsu.customer_service.domain.use_cases.customer.update.CustomerUpdateInputValidator;
import com.devsu.customer_service.domain.use_cases.customer.update.CustomerUpdateUseCase;
import com.devsu.customer_service.domain.use_cases.customer.update_credentials.CustomerCredentialUpdateUseCase;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCases {

	@Bean
	public CustomerCreateUseCase customerCreateUseCase(
	  CustomerRepository customerRepository,
	  CustomerCreateInputValidator customerCreateInputValidator,
	  PasswordService passwordEncoder,
	  ApplicationEventPublisher applicationEventPublisher
	) {
		return new CustomerCreateUseCase(
		  customerRepository,
		  customerCreateInputValidator,
		  passwordEncoder,
		  applicationEventPublisher
		);
	}

	@Bean
	public CustomerDetailUseCase customerDetailUseCase(CustomerRepository customerRepository) {
		return new CustomerDetailUseCase(customerRepository);
	}

	@Bean
	public CustomerUpdateUseCase customerUpdateUseCase(
	  CustomerRepository customerRepository,
	  CustomerUpdateInputValidator customerUpdateInputValidator,
	  ApplicationEventPublisher applicationEventPublisher
	) {
		return new CustomerUpdateUseCase(
		  customerRepository,
		  customerUpdateInputValidator,
		  applicationEventPublisher
		);
	}

	@Bean
	public CustomerPartialUpdateUseCase customerPartialUpdateUseCase(
	  CustomerRepository customerRepository,
	  CustomerPartialUpdateInputValidator partialUpdateInputValidator
	) {
		return new CustomerPartialUpdateUseCase(
		  customerRepository,
		  partialUpdateInputValidator
		);
	}

	@Bean
	public CustomerCredentialUpdateUseCase credentialUpdateUseCase(
	  CustomerRepository customerRepository,
	  PasswordService passwordEncoder
	) {
		return new CustomerCredentialUpdateUseCase(
		  customerRepository,
		  passwordEncoder
		);
	}

	@Bean
	public CustomerPaginatedListUseCase customerPaginatedListUseCase(
	  CustomerRepository customerRepository
	) {
		return new CustomerPaginatedListUseCase(customerRepository);
	}

	@Bean
	public CustomerDeleteUseCase customerDeleteUseCase(
	  CustomerRepository customerRepository,
	  ApplicationEventPublisher applicationEventPublisher
	) {
		return new CustomerDeleteUseCase(
		  customerRepository,
		  applicationEventPublisher
		);
	}

}
