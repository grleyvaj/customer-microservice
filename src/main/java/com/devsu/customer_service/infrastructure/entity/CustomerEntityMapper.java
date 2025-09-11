package com.devsu.customer_service.infrastructure.entity;

import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.domain.helpers.Generator;
import com.devsu.customer_service.domain.use_cases.customer.create.CustomerCreateInput;

import java.time.LocalDateTime;

public class CustomerEntityMapper implements Mapper<CustomerCreateInput, CustomerEntity> {

	@Override
	public CustomerEntity map(CustomerCreateInput customerCreateInput) {
		CustomerEntity entity = new CustomerEntity();
		entity.setId(Generator.ulid());
		entity.setClientId(customerCreateInput.getClientId());
		entity.setName(customerCreateInput.getName());
		entity.setGender(customerCreateInput.getGender());
		entity.setAge(customerCreateInput.getAge());
		entity.setAddress(customerCreateInput.getAddress());
		entity.setPhone(customerCreateInput.getPhone());
		entity.setEncryptedPassword(customerCreateInput.getEncryptedPassword());
		entity.setIsActive(Boolean.TRUE);
		entity.setIsDeleted(Boolean.FALSE);
		entity.setCreatedAt(LocalDateTime.now());

		return entity;
	}

}
