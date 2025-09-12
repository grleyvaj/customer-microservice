package com.devsu.customer_service.infrastructure.entity;

import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.domain.helpers.Generator;
import com.devsu.customer_service.domain.use_cases.customer.create.CustomerCreateInput;

import java.time.LocalDateTime;

public class CustomerEntityMapper implements Mapper<CustomerCreateInput, CustomerEntity> {

	@Override
	public CustomerEntity map(CustomerCreateInput customerCreateInput) {
		LocalDateTime current = LocalDateTime.now();

		CustomerEntity entity = (CustomerEntity)new CustomerEntity()
		  .setId(Generator.ulid())
		  .setName(customerCreateInput.getName())
		  .setGender(customerCreateInput.getGender())
		  .setAge(customerCreateInput.getAge())
		  .setAddress(customerCreateInput.getAddress())
		  .setPhone(customerCreateInput.getPhone())
		  .setIsActive(Boolean.TRUE)
		  .setIsDeleted(Boolean.FALSE)
		  .setCreatedAt(current)
		  .setUpdatedAt(current);

		entity.setClientId(customerCreateInput.getClientId());
		entity.setEncryptedPassword(customerCreateInput.getEncryptedPassword());

		return entity;
	}

}
