package com.devsu.customer_service.application.beans;

import com.devsu.customer_service.application.handlers.response.ValidationErrorResponseCreator;
import com.devsu.customer_service.domain.repository.CustomerRepository;
import com.devsu.customer_service.domain.use_cases.customer._validator.ClientUniqueValidator;
import com.devsu.customer_service.domain.use_cases.customer.create.CustomerCreateInputValidator;
import com.devsu.customer_service.domain.use_cases.customer.partial_update.CustomerPartialUpdateInputValidator;
import com.devsu.customer_service.domain.use_cases.customer.update.CustomerUpdateInputValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Validators {

	@Bean
	public ValidationErrorResponseCreator validationErrorResponseCreator(MessageSource messageSource) {
		return new ValidationErrorResponseCreator(messageSource);
	}

	@Bean
	public CustomerCreateInputValidator customerCreateInputValidator(CustomerRepository customerRepository) {
		return new CustomerCreateInputValidator(customerRepository);
	}

	@Bean
	public ClientUniqueValidator clientUniqueValidator(CustomerRepository repository) {
		return new ClientUniqueValidator(repository);
	}

	@Bean
	public CustomerUpdateInputValidator customerUpdateInputValidator(
	  CustomerRepository customerRepository,
	  ClientUniqueValidator clientUniqueValidator
	) {
		return new CustomerUpdateInputValidator(
		  customerRepository,
		  clientUniqueValidator
		);
	}

	@Bean
	public CustomerPartialUpdateInputValidator partialUpdateInputValidator(
	  CustomerRepository customerRepository,
	  ClientUniqueValidator clientUniqueValidator
	) {
		return new CustomerPartialUpdateInputValidator(
		  customerRepository,
		  clientUniqueValidator
		);
	}
}
