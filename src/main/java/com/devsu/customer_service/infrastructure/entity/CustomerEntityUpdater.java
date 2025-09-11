package com.devsu.customer_service.infrastructure.entity;

import com.devsu.customer_service.domain.use_cases.customer.partial_update.CustomerPartialUpdateInput;
import com.devsu.customer_service.domain.use_cases.customer.update.CustomerUpdateInput;

import java.time.LocalDateTime;

public class CustomerEntityUpdater {

	public CustomerEntity update(
	  CustomerEntity entity,
	  CustomerUpdateInput customerUpdateInput
	) {
		entity.setClientId(customerUpdateInput.getClientId());
		entity.setName(customerUpdateInput.getName());
		entity.setGender(customerUpdateInput.getGender());
		entity.setAge(customerUpdateInput.getAge());
		entity.setAddress(customerUpdateInput.getAddress());
		entity.setPhone(customerUpdateInput.getPhone());
		entity.setUpdatedAt(LocalDateTime.now());

		return entity;
	}

	public CustomerEntity update(
	  CustomerEntity entity,
	  CustomerPartialUpdateInput customerPartialUpdateInput
	) {
		customerPartialUpdateInput.getClientId().ifPresent(entity::setClientId);
		customerPartialUpdateInput.getName().ifPresent(entity::setName);
		customerPartialUpdateInput.getGender().ifPresent(entity::setGender);
		customerPartialUpdateInput.getAge().ifPresent(entity::setAge);
		customerPartialUpdateInput.getAddress().ifPresent(entity::setAddress);
		customerPartialUpdateInput.getPhone().ifPresent(entity::setPhone);
		entity.setUpdatedAt(LocalDateTime.now());

		return entity;
	}


}
