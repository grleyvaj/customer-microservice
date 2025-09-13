package com.devsu.customer_service.infrastructure.entity;

import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.domain.models.Customer;

public class CustomerMapper implements Mapper<CustomerEntity, Customer> {

	@Override
	public Customer map(CustomerEntity entity) {
		return (Customer)new Customer(
		  entity.getId(),
		  entity.getClientId(),
		  entity.getName(),
		  entity.getGender(),
		  entity.getAge(),
		  entity.getAddress(),
		  entity.getPhone(),
		  entity.getEncryptedPassword(),
		  entity.getIsActive(),
		  entity.getIsDeleted(),
		  entity.getCreatedAt(),
		  entity.getUpdatedAt()
		)
		  .setDeletedAt(entity.getDeletedAt());
	}

}
