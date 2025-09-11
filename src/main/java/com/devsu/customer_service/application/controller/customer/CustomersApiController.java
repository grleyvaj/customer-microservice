package com.devsu.customer_service.application.controller.customer;

import com.devsu.customer_service.application.contract.ListMapper;
import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.application.controller.customer.create.CustomerCreateRequest;
import com.devsu.customer_service.application.controller.customer.detail.CustomerDetailResponse;
import com.devsu.customer_service.application.controller.customer.list.CustomerSummaryListResponse;
import com.devsu.customer_service.application.controller.customer.list.CustomerSummaryResponse;
import com.devsu.customer_service.application.controller.customer.partial_update.CustomerPartialUpdateRequest;
import com.devsu.customer_service.application.controller.customer.update.CustomerUpdateRequest;
import com.devsu.customer_service.application.controller.customer.update_credentials.CustomerCredentialsUpdateRequest;
import com.devsu.customer_service.application.controller.pagination.response.PageResponse;
import com.devsu.customer_service.application.controller.pagination.response.PaginationFilters;
import com.devsu.customer_service.domain.exception.ResourceNotFoundException;
import com.devsu.customer_service.domain.exception.ValidationException;
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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomersApiController implements CustomersApi {

	private final CustomerCreateUseCase customerCreateUseCase;
	private final CustomerDetailUseCase customerDetailUseCase;
	private final CustomerUpdateUseCase customerUpdateUseCase;
	private final CustomerPartialUpdateUseCase customerPartialUpdateUseCase;
	private final CustomerCredentialUpdateUseCase credentialUpdateUseCase;
	private final CustomerPaginatedListUseCase customerPaginatedListUseCase;
	private final CustomerDeleteUseCase customerDeleteUseCase;
	private final Mapper<CustomerCreateRequest, CustomerCreateInput> customerCreateInputMapper;
	private final Mapper<Customer, CustomerDetailResponse> customerDetailResponseMapper;
	private final Mapper<CustomerUpdateRequest, CustomerUpdateInput> customerUpdateInputMapper;
	private final Mapper<CustomerPartialUpdateRequest, CustomerPartialUpdateInput> customerPartialUpdateInputMapper;
	private final ListMapper<Customer, CustomerSummaryResponse> customerSummaryResponseListMapper;
	private final Mapper<Pagination, PageResponse> pageResponseMapper;

	@Override
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDetailResponse> create(
	  @RequestBody @Valid CustomerCreateRequest request
	) throws ValidationException {

		Customer customer = this.customerCreateUseCase.execute(
		  this.customerCreateInputMapper.map(request)
		);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
		  .path("/{id}")
		  .buildAndExpand(customer.getId())
		  .toUri();

		return ResponseEntity.created(uri).body(this.customerDetailResponseMapper.map(customer));
	}

	@Override
	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDetailResponse> detail(
	  @PathVariable(value = "id") String id
	) throws ResourceNotFoundException {
		return ResponseEntity.ok(
		  this.customerDetailResponseMapper.map(
			this.customerDetailUseCase.execute(id)
		  )
		);
	}

	@Override
	@PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDetailResponse> updateCustomer(
	  @PathVariable(value = "id") String id,
	  @RequestBody @Valid CustomerUpdateRequest request
	) throws ResourceNotFoundException, ValidationException {

		return ResponseEntity.ok(
		  this.customerDetailResponseMapper.map(
			this.customerUpdateUseCase.execute(
			  id, this.customerUpdateInputMapper.map(request)
			)
		  )
		);
	}

	@Override
	@PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDetailResponse> updatePartialCustomer(
	  @PathVariable(value = "id") String id,
	  @RequestBody @Valid CustomerPartialUpdateRequest request
	) throws ResourceNotFoundException, ValidationException {

		return ResponseEntity.ok(
		  this.customerDetailResponseMapper.map(
			this.customerPartialUpdateUseCase.execute(
			  id, this.customerPartialUpdateInputMapper.map(request)
			)
		  )
		);
	}

	@Override
	@PutMapping(value = "/{id}/credentials", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateCredentials(
	  @PathVariable(value = "id") String id,
	  @RequestBody @Valid CustomerCredentialsUpdateRequest request
	) throws ResourceNotFoundException, ValidationException {
		this.credentialUpdateUseCase.execute(id, request.getOldPassword(), request.getNewPassword());

		return ResponseEntity.ok().build();
	}

	@Override
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerSummaryListResponse> getAll(
	  @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
	  @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
	  @RequestParam(value = "direction", defaultValue = "DESC") SortDirection sortDirection
	) {
		PaginationModel<Customer> customerPage = this.customerPaginatedListUseCase.execute(
		  new PaginationFilters()
			.setPage(page)
			.setSize(size)
			.setSort(sortDirection)
		);

		return ResponseEntity.ok(
		  new CustomerSummaryListResponse(
			this.pageResponseMapper.map(customerPage.getPagination()),
			this.customerSummaryResponseListMapper.map(customerPage.getItems())
		  )
		);
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
		this.customerDeleteUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}

}
