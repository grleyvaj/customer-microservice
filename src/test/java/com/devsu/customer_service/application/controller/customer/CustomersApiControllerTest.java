package com.devsu.customer_service.application.controller.customer;

import com.devsu.customer_service.application.contract.ListMapper;
import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.application.controller.customer.create.CustomerCreateRequest;
import com.devsu.customer_service.application.controller.customer.detail.CustomerDetailResponse;
import com.devsu.customer_service.application.controller.customer.list.CustomerSummaryResponse;
import com.devsu.customer_service.application.controller.customer.partial_update.CustomerPartialUpdateRequest;
import com.devsu.customer_service.application.controller.customer.update.CustomerUpdateRequest;
import com.devsu.customer_service.application.controller.pagination.response.PageResponse;
import com.devsu.customer_service.builder.CustomerBuilder;
import com.devsu.customer_service.builder.CustomerSummaryResponseBuilder;
import com.devsu.customer_service.builder.PageResponseBuilder;
import com.devsu.customer_service.domain.exception.ResourceNotFoundException;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.models.SortDirection;
import com.devsu.customer_service.domain.pagination.Pagination;
import com.devsu.customer_service.domain.pagination.PaginationModel;
import com.devsu.customer_service.domain.use_cases.customer.create.CustomerCreateInput;
import com.devsu.customer_service.domain.use_cases.customer.create.CustomerCreateUseCase;
import com.devsu.customer_service.domain.use_cases.customer.delete.CustomerDeleteUseCase;
import com.devsu.customer_service.domain.use_cases.customer.detail.CustomerDetailUseCase;
import com.devsu.customer_service.domain.use_cases.customer.list.CustomerPaginatedListUseCase;
import com.devsu.customer_service.domain.use_cases.customer.partial_update.CustomerPartialUpdateInput;
import com.devsu.customer_service.domain.use_cases.customer.partial_update.CustomerPartialUpdateUseCase;
import com.devsu.customer_service.domain.use_cases.customer.update.CustomerUpdateInput;
import com.devsu.customer_service.domain.use_cases.customer.update.CustomerUpdateUseCase;
import com.devsu.customer_service.domain.use_cases.customer.update_credentials.CustomerCredentialUpdateUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomersApiControllerTest {

	@InjectMocks
	private CustomersApiController controller;

	@Mock
	private CustomerCreateUseCase customerCreateUseCase;

	@Mock
	private CustomerDetailUseCase customerDetailUseCase;

	@Mock
	private CustomerUpdateUseCase customerUpdateUseCase;

	@Mock
	private CustomerPartialUpdateUseCase customerPartialUpdateUseCase;

	@Mock
	private CustomerCredentialUpdateUseCase credentialUpdateUseCase;

	@Mock
	private CustomerPaginatedListUseCase customerPaginatedListUseCase;

	@Mock
	private CustomerDeleteUseCase customerDeleteUseCase;

	@Mock
	private Mapper<CustomerCreateRequest, CustomerCreateInput> customerCreateInputMapper;

	@Mock
	private Mapper<Customer, CustomerDetailResponse> customerDetailResponseMapper;

	@Mock
	private Mapper<CustomerUpdateRequest, CustomerUpdateInput> customerUpdateInputMapper;

	@Mock
	private Mapper<CustomerPartialUpdateRequest, CustomerPartialUpdateInput> customerPartialUpdateInputMapper;

	@Mock
	private ListMapper<Customer, CustomerSummaryResponse> customerSummaryResponseListMapper;

	@Mock
	private Mapper<Pagination, PageResponse> pageResponseMapper;

	@BeforeEach
	public void setUp() {
		this.controller = new CustomersApiController(
		  this.customerCreateUseCase,
		  this.customerDetailUseCase,
		  this.customerUpdateUseCase,
		  this.customerPartialUpdateUseCase,
		  this.credentialUpdateUseCase,
		  this.customerPaginatedListUseCase,
		  this.customerDeleteUseCase,
		  this.customerCreateInputMapper,
		  this.customerDetailResponseMapper,
		  this.customerUpdateInputMapper,
		  this.customerPartialUpdateInputMapper,
		  this.customerSummaryResponseListMapper,
		  this.pageResponseMapper
		);
	}

	@Test
	public void when_get_customer_detail_is_called_then_detail_use_case_is_used() throws ResourceNotFoundException {
		this.controller.detail("::id::");

		verify(this.customerDetailUseCase).execute("::id::");
	}

	@Test
	void when_request_is_received_then_paging_is_created() {
		doReturn(List.of(new CustomerSummaryResponseBuilder().build(2)))
		  .when(this.customerSummaryResponseListMapper).map(anyList());

		doReturn(new PageResponseBuilder().build(2))
		  .when(this.pageResponseMapper).map(any());

		doReturn(
		  new PaginationModel<>(
			new Pagination(10, 5, 50, 5, Boolean.FALSE),
			List.of(new CustomerBuilder().build(1))
		  )
		).when(this.customerPaginatedListUseCase).execute(any(/* PaginationFilters.class */));

		this.controller.getAll(8, 7, SortDirection.ASC);

		verify(this.customerPaginatedListUseCase).execute(argThat(filters ->
		  filters != null
		  && Integer.valueOf(8).equals(filters.getPage())
		  && Integer.valueOf(7).equals(filters.getSize())
		  && SortDirection.ASC.equals(filters.getSort())
		));
	}


}