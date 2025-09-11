package com.devsu.customer_service.domain.pagination;

import lombok.Data;

import java.util.List;

@Data
public class PaginationModel<T> {

	private final Pagination pagination;
	private final List<T> items;

}
