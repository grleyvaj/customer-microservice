package com.devsu.customer_service.application.beans;

import com.devsu.customer_service.domain.services.CustomerPublisherService;
import com.devsu.customer_service.infrastructure.events.CustomerCreatedEventListener;
import com.devsu.customer_service.infrastructure.events.CustomerDeletedEventListener;
import com.devsu.customer_service.infrastructure.events.CustomerUpdatedEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class Listeners {


	@Bean
	public CustomerCreatedEventListener customerCreatedEventListener(
	  CustomerPublisherService publisherService,
	  RetryTemplate retryTemplate
	) {
		return new CustomerCreatedEventListener(publisherService, retryTemplate);
	}

	@Bean
	public CustomerUpdatedEventListener customerUpdatedEventListener(
	  CustomerPublisherService publisherService,
	  RetryTemplate retryTemplate
	) {
		return new CustomerUpdatedEventListener(publisherService, retryTemplate);
	}

	@Bean
	public CustomerDeletedEventListener customerDeletedEventListener(
	  CustomerPublisherService publisherService,
	  RetryTemplate retryTemplate
	) {
		return new CustomerDeletedEventListener(publisherService, retryTemplate);
	}

}
