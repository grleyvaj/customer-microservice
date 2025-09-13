package com.devsu.customer_service.builder;

import com.devsu.customer_service.application.controller.customer.list.CustomerSummaryResponse;

public class CustomerSummaryResponseBuilder extends Builder<CustomerSummaryResponse> {

	@Override
	public CustomerSummaryResponse build(int index) {
		return new CustomerSummaryResponse()
		  .setId("::id-" + index + "::")
		  .setName("::name-" + index + "::")
		  .setAddress("::address-" + index + "::")
		  .setPhone("+556262626" + index)
		  .setEncryptedPassword("::encrypted::")
		  .setState(Boolean.TRUE);
	}

}
