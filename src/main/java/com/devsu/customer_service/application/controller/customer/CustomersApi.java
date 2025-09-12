package com.devsu.customer_service.application.controller.customer;

import com.devsu.customer_service.application.controller.customer.create.CustomerCreateRequest;
import com.devsu.customer_service.application.controller.customer.detail.CustomerDetailResponse;
import com.devsu.customer_service.application.controller.customer.list.CustomerSummaryListResponse;
import com.devsu.customer_service.application.controller.customer.partial_update.CustomerPartialUpdateRequest;
import com.devsu.customer_service.application.controller.customer.update.CustomerUpdateRequest;
import com.devsu.customer_service.application.controller.customer.update_credentials.CustomerCredentialsUpdateRequest;
import com.devsu.customer_service.application.handlers.response.ErrorResponse;
import com.devsu.customer_service.application.handlers.response.ValidationErrorResponseList;
import com.devsu.customer_service.domain.exception.ResourceNotFoundException;
import com.devsu.customer_service.domain.exception.ValidationException;
import com.devsu.customer_service.domain.models.SortDirection;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "${api.tag.customers.name}", description = "${api.tag.customers.description}")
public interface CustomersApi {

	@Operation(
	  summary = "${api.customer.create.summary}",
	  description = "${api.customer.create.description}",
	  operationId = "createCustomer"
	)
	@ApiResponses({
	  @ApiResponse(responseCode = "201", description = "${api.customer.create.response.201.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDetailResponse.class))),
	  @ApiResponse(responseCode = "400", description = "${api.response.400.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ValidationErrorResponseList.class))),
	  @ApiResponse(responseCode = "401", description = "${api.response.401.description}", content = @Content),
	  @ApiResponse(responseCode = "403", description = "${api.response.403.description}", content = @Content),
	  @ApiResponse(responseCode = "404", description = "${api.response.404.description}",
		content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
	  @ApiResponse(responseCode = "500", description = "${api.response.500.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<CustomerDetailResponse> create(
	  @RequestBody(required = true) @Valid CustomerCreateRequest request
	) throws ValidationException;

	@Operation(
	  summary = "${api.customer.detail.summary}",
	  description = "${api.customer.detail.description}",
	  operationId = "getDetailCustomer"
	)
	@ApiResponses({
	  @ApiResponse(responseCode = "200", description = "${api.customer.detail.response.200.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDetailResponse.class))),
	  @ApiResponse(responseCode = "401", description = "${api.response.401.description}", content = @Content),
	  @ApiResponse(responseCode = "403", description = "${api.response.403.description}", content = @Content),
	  @ApiResponse(responseCode = "404", description = "${api.response.404.description}",
		content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
	  @ApiResponse(responseCode = "500", description = "${api.response.500.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
	})
	ResponseEntity<CustomerDetailResponse> detail(
	  @Parameter(
		in = ParameterIn.PATH,
		name = "id",
		description = "${customer.id.description}",
		required = true,
		schema = @Schema(type = "string", format = "ulid", example = "01HZX0SYT8P7TQ2J0GKH2HF26R")
	  ) String id
	) throws ResourceNotFoundException;

	@Operation(
	  summary = "${api.customer.update.summary}",
	  description = "${api.customer.update.description}",
	  operationId = "updateCustomer"
	)
	@ApiResponses({
	  @ApiResponse(responseCode = "200", description = "${api.customer.update.response.200.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDetailResponse.class))),
	  @ApiResponse(responseCode = "400", description = "${api.response.400.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ValidationErrorResponseList.class))),
	  @ApiResponse(responseCode = "401", description = "${api.response.401.description}", content = @Content),
	  @ApiResponse(responseCode = "403", description = "${api.response.403.description}", content = @Content),
	  @ApiResponse(responseCode = "404", description = "${api.response.404.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
	  @ApiResponse(responseCode = "500", description = "${api.response.500.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
	})
	ResponseEntity<CustomerDetailResponse> updateCustomer(
	  @Parameter(
		in = ParameterIn.PATH,
		name = "id",
		description = "${customer.id.description}",
		required = true,
		schema = @Schema(type = "string", format = "ulid", example = "01HZX0SYT8P7TQ2J0GKH2HF26R")
	  ) String id,
	  @RequestBody(required = true) @Valid CustomerUpdateRequest request
	) throws ResourceNotFoundException, ValidationException;

	@Operation(
	  summary = "${api.customer.updatePartial.summary}",
	  description = "${api.customer.updatePartial.description}",
	  operationId = "updatePartialCustomer"
	)
	@ApiResponses({
	  @ApiResponse(responseCode = "200", description = "${api.customer.updatePartial.response.200.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDetailResponse.class))),
	  @ApiResponse(responseCode = "400", description = "${api.response.400.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ValidationErrorResponseList.class))),
	  @ApiResponse(responseCode = "401", description = "${api.response.401.description}", content = @Content),
	  @ApiResponse(responseCode = "403", description = "${api.response.403.description}", content = @Content),
	  @ApiResponse(responseCode = "404", description = "${api.response.404.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
	  @ApiResponse(responseCode = "500", description = "${api.response.500.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
	})
	ResponseEntity<CustomerDetailResponse> updatePartialCustomer(
	  @Parameter(
		in = ParameterIn.PATH,
		name = "id",
		description = "${customer.id.description}",
		required = true,
		schema = @Schema(type = "string", format = "ulid", example = "01HZX0SYT8P7TQ2J0GKH2HF26R")
	  ) String id,
	  @RequestBody(required = true) @Valid CustomerPartialUpdateRequest request
	) throws ResourceNotFoundException, ValidationException;

	@Operation(
	  summary = "${api.customer.updateCredentials.summary}",
	  description = "${api.customer.updateCredentials.description}",
	  operationId = "updateCustomerCredentials"
	)
	@ApiResponses({
	  @ApiResponse(responseCode = "200", description = "${api.customer.updateCredentials.response.200.description}", content = @Content),
	  @ApiResponse(responseCode = "400", description = "${api.response.400.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ValidationErrorResponseList.class))),
	  @ApiResponse(responseCode = "401", description = "${api.response.401.description}", content = @Content),
	  @ApiResponse(responseCode = "403", description = "${api.response.403.description}", content = @Content),
	  @ApiResponse(responseCode = "404", description = "${api.response.404.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
	  @ApiResponse(responseCode = "500", description = "${api.response.500.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
	})
	ResponseEntity<Void> updateCredentials(
	  @Parameter(
		in = ParameterIn.PATH,
		name = "id",
		description = "${customer.id.description}",
		required = true,
		schema = @Schema(type = "string", format = "ulid", example = "01HZX0SYT8P7TQ2J0GKH2HF26R")
	  ) String id,
	  @RequestBody(required = true) @Valid CustomerCredentialsUpdateRequest request
	) throws ResourceNotFoundException, ValidationException;

	@Operation(
	  summary = "${api.customer.all.summary}",
	  description = "${api.customer.all.description}",
	  operationId = "getAllCustomers"
	)
	@ApiResponses(value = {
	  @ApiResponse(responseCode = "200", description = "${api.customer.all.response.200.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerSummaryListResponse.class))),
	  @ApiResponse(responseCode = "401", description = "${api.response.401.description}", content = @Content),
	  @ApiResponse(responseCode = "403", description = "${api.response.403.description}", content = @Content),
	  @ApiResponse(responseCode = "500", description = "${api.response.500.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
	})
	ResponseEntity<CustomerSummaryListResponse> getAll(
	  @Parameter(in = ParameterIn.QUERY, name = "page", description = "${api.op.paginatedList.parameter.page.description}", example = "0") Integer page,
	  @Parameter(in = ParameterIn.QUERY, name = "size", description = "${api.op.paginatedList.parameter.size.description}", example = "10") Integer size,
	  @Parameter(in = ParameterIn.QUERY, name = "sort", description = "${api.op.paginatedList.parameter.sort.description}", example = "ASC") SortDirection sort
	);

	@Operation(
	  summary = "${api.customer.delete.summary}",
	  description = "${api.customer.delete.description}",
	  operationId = "deleteCustomer"
	)
	@ApiResponses({
	  @ApiResponse(responseCode = "204", description = "${api.customer.delete.response.204.description}", content = @Content),
	  @ApiResponse(responseCode = "401", description = "${api.response.401.description}", content = @Content),
	  @ApiResponse(responseCode = "403", description = "${api.response.403.description}", content = @Content),
	  @ApiResponse(responseCode = "404", description = "${api.response.404.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
	  @ApiResponse(responseCode = "500", description = "${api.response.500.description}",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
	})
	ResponseEntity<Void> delete(
	  @Parameter(
		in = ParameterIn.PATH,
		name = "id",
		description = "${customer.id.description}",
		required = true,
		schema = @Schema(type = "string", format = "ulid", example = "01HZX0SYT8P7TQ2J0GKH2HF26R")
	  ) String id
	) throws ResourceNotFoundException, ValidationException;

}
