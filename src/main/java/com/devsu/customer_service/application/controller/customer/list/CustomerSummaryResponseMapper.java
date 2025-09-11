package com.devsu.customer_service.application.controller.customer.list;

import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.domain.models.Customer;

public class CustomerSummaryResponseMapper implements Mapper<Customer, CustomerSummaryResponse> {

	@Override
	public CustomerSummaryResponse map(Customer model) {
		return new CustomerSummaryResponse()
		  .setId(model.getId())
		  .setName(model.getName())
		  .setAddress(model.getAddress())
		  .setPhone(model.getPhone())
		  .setEncryptedPassword(model.getEncryptedPassword())
		  .setState(model.getIsActive());
	}

}
