package com.devsu.customer_service.infrastructure.entity;

import com.devsu.customer_service.domain.use_cases.customer.partial_update.CustomerPartialUpdateInput;
import com.devsu.customer_service.domain.use_cases.customer.update.CustomerUpdateInput;

import java.time.LocalDateTime;

public class CustomerEntityUpdater {

	public CustomerEntity update(
	  CustomerEntity entity,
	  CustomerUpdateInput customerUpdateInput
	) {
		entity
		  .setClientId(customerUpdateInput.getClientId())
		  .setName(customerUpdateInput.getName())
		  .setGender(customerUpdateInput.getGender())
		  .setAge(customerUpdateInput.getAge())
		  .setAddress(customerUpdateInput.getAddress())
		  .setPhone(customerUpdateInput.getPhone())
		  .setUpdatedAt(LocalDateTime.now());

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
