package com.devsu.customer_service.application.controller.customer.detail;

import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.domain.models.Customer;

public class CustomerDetailResponseMapper implements Mapper<Customer, CustomerDetailResponse> {

	@Override
	public CustomerDetailResponse map(Customer model) {
		return new CustomerDetailResponse()
		  .setId(model.getId())
		  .setClientId(model.getClientId())
		  .setName(model.getName())
		  .setGender(model.getGender().getName())
		  .setAge(model.getAge())
		  .setAddress(model.getAddress())
		  .setPhone(model.getPhone())
		  .setEncryptedPassword(model.getEncryptedPassword())
		  .setState(model.getIsActive());
	}

}
