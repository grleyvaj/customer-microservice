package com.devsu.customer_service.builder;

import com.devsu.customer_service.application.controller.pagination.response.PageResponse;

public class PageResponseBuilder extends Builder<PageResponse> {

	@Override
	public PageResponse build(int index) {
		return new PageResponse(
		  index * 2,
		  index,
		  index * 10L,
		  index
		);
	}

}
