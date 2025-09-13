package com.devsu.customer_service.application.beans;

import com.devsu.customer_service.application.configuration.message.MessagingConfig;
import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.services.PasswordService;
import com.devsu.customer_service.infrastructure.rabbitmq.customer.RabbitCustomerPublisherService;
import com.devsu.customer_service.infrastructure.rabbitmq.customer.create.CustomerCreatedMessage;
import com.devsu.customer_service.infrastructure.rabbitmq.customer.delete.CustomerDeletedMessage;
import com.devsu.customer_service.infrastructure.rabbitmq.customer.update.CustomerUpdatedMessage;
import com.devsu.customer_service.infrastructure.services.PasswordEncoderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Services {

	@Bean
	public PasswordService passwordService(PasswordEncoder encoder) {
		return new PasswordEncoderService(encoder);
	}

	@Bean
	public RabbitCustomerPublisherService customerEventPublisher(
	  RabbitTemplate rabbitTemplate,
	  MessagingConfig messagingProperties,
	  Mapper<Customer, CustomerCreatedMessage> customerCreatedMessageMapper,
	  Mapper<Customer, CustomerUpdatedMessage> customerUpdatedMessageMapper,
	  Mapper<Customer, CustomerDeletedMessage> customerDeletedMessageMapper
	) {
		return new RabbitCustomerPublisherService(
		  rabbitTemplate,
		  messagingProperties,
		  customerCreatedMessageMapper,
		  customerUpdatedMessageMapper,
		  customerDeletedMessageMapper
		);
	}

}
