package com.devsu.customer_service.application.beans;

import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.use_cases.customer.create.CustomerCreateInput;
import com.devsu.customer_service.infrastructure.entity.CustomerEntity;
import com.devsu.customer_service.infrastructure.entity.CustomerEntityUpdater;
import com.devsu.customer_service.infrastructure.persistence.JpaCustomerRepository;
import com.devsu.customer_service.infrastructure.postgres.PostgresCustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Repositories {

	@Bean
	public PostgresCustomerRepository postgresCustomerRepository(
	  JpaCustomerRepository jpaCustomerRepository,
	  Mapper<CustomerCreateInput, CustomerEntity> customerEntityMapper,
	  Mapper<CustomerEntity, Customer> customerMapper,
	  CustomerEntityUpdater customerEntityUpdater
	) {
		return new PostgresCustomerRepository(
		  jpaCustomerRepository,
		  customerEntityMapper,
		  customerMapper,
		  customerEntityUpdater
		);
	}

}
