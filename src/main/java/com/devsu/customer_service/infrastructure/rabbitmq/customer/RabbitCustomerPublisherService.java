package com.devsu.customer_service.infrastructure.rabbitmq.customer;

import com.devsu.customer_service.application.configuration.message.MessagingConfig;
import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.domain.exception.MessagingPublisherException;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.services.CustomerPublisherService;
import com.devsu.customer_service.infrastructure.rabbitmq.customer.create.CustomerCreatedMessage;
import com.devsu.customer_service.infrastructure.rabbitmq.customer.delete.CustomerDeletedMessage;
import com.devsu.customer_service.infrastructure.rabbitmq.customer.update.CustomerUpdatedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
@RequiredArgsConstructor
public class RabbitCustomerPublisherService implements CustomerPublisherService {

	private final RabbitTemplate rabbitTemplate;
	private final MessagingConfig messagingProperties;
	private final Mapper<Customer, CustomerCreatedMessage> customerCreatedMessageMapper;
	private final Mapper<Customer, CustomerUpdatedMessage> customerUpdatedMessageMapper;
	private final Mapper<Customer, CustomerDeletedMessage> customerDeletedMessageMapper;

	public void publishCustomerCreated(Customer createdCustomer) throws MessagingPublisherException {
		send(
		  this.customerCreatedMessageMapper.map(createdCustomer),
		  this.messagingProperties.getCreated().getRoutingKey()
		);
	}

	public void publishCustomerUpdated(Customer updatedCustomer) throws MessagingPublisherException {
		send(
		  this.customerUpdatedMessageMapper.map(updatedCustomer),
		  this.messagingProperties.getUpdated().getRoutingKey()
		);
	}

	public void publishCustomerDeleted(Customer deletedCustomer) throws MessagingPublisherException {
		send(
		  this.customerDeletedMessageMapper.map(deletedCustomer),
		  this.messagingProperties.getDeleted().getRoutingKey()
		);
	}

	private void send(Object event, String routingKey) throws MessagingPublisherException {
		try {
			rabbitTemplate.convertAndSend(this.messagingProperties.getExchange(), routingKey, event);
		} catch(Exception ex) {
			throw new MessagingPublisherException("Error sending event [" + routingKey + "] to RabbitMQ", ex);
		}
	}

}
