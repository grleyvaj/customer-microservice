package com.devsu.customer_service.application.controller.pagination.response;

import com.devsu.customer_service.domain.models.SortDirection;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PaginationFilters {

	private int page;
	private int size;
	private SortDirection sort;

}
