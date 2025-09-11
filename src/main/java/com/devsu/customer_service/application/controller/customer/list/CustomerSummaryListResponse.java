package com.devsu.customer_service.application.controller.customer.list;

import com.devsu.customer_service.application.controller.pagination.response.PageResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CustomerSummaryListResponse {

	@JsonProperty("page")
	private final PageResponse pagination;

	@JsonProperty("customers")
	private final List<CustomerSummaryResponse> customers;


}
