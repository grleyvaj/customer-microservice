package com.devsu.customer_service.infrastructure.postgres;

import com.devsu.customer_service.application.contract.Mapper;
import com.devsu.customer_service.application.controller.pagination.response.PaginationFilters;
import com.devsu.customer_service.domain.exception.ResourceNotFoundException;
import com.devsu.customer_service.domain.models.Customer;
import com.devsu.customer_service.domain.pagination.Pagination;
import com.devsu.customer_service.domain.pagination.PaginationModel;
import com.devsu.customer_service.domain.repository.CustomerRepository;
import com.devsu.customer_service.domain.use_cases.customer.create.CustomerCreateInput;
import com.devsu.customer_service.domain.use_cases.customer.partial_update.CustomerPartialUpdateInput;
import com.devsu.customer_service.domain.use_cases.customer.update.CustomerUpdateInput;
import com.devsu.customer_service.infrastructure.entity.CustomerEntity;
import com.devsu.customer_service.infrastructure.entity.CustomerEntityUpdater;
import com.devsu.customer_service.infrastructure.entity.CustomerIdProjection;
import com.devsu.customer_service.infrastructure.persistence.JpaCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
public class PostgresCustomerRepository implements CustomerRepository {

	private final JpaCustomerRepository jpaCustomerRepository;
	private final Mapper<CustomerCreateInput, CustomerEntity> customerEntityMapper;
	private final Mapper<CustomerEntity, Customer> customerMapper;
	private final CustomerEntityUpdater customerEntityUpdater;

	private static final String SORT_BY_ID = "id";  // 👈 constante estática

	@Override
	public Customer create(CustomerCreateInput customerCreateInput) {
		return this.customerMapper.map(
		  this.jpaCustomerRepository.save(
			this.customerEntityMapper.map(customerCreateInput)
		  )
		);
	}

	@Override
	public boolean existsByClient(String clientId) {
		return this.jpaCustomerRepository.existsByClientIdAndIsDeletedFalse(clientId);
	}

	@Override
	public Optional<Customer> findById(String customerId) {
		return this.jpaCustomerRepository.findByIdAndIsDeletedFalse(customerId)
		  .map(this.customerMapper::map);
	}

	@Override
	public Optional<String> findByClient(String clientId) {
		return this.jpaCustomerRepository.findByClientIdAndIsDeletedFalse(clientId)
		  .map(CustomerIdProjection::getId);
	}

	@Override
	public Customer update(
	  String customerId,
	  CustomerUpdateInput customerUpdateInput
	) throws ResourceNotFoundException {
		CustomerEntity customer = this.jpaCustomerRepository.findByIdAndIsDeletedFalse(customerId)
		  .orElseThrow(() -> new ResourceNotFoundException("Customer %s not found".formatted(customerId)));

		return this.customerMapper.map(
		  this.jpaCustomerRepository.save(
			this.customerEntityUpdater.update(customer, customerUpdateInput)
		  )
		);
	}

	@Override
	public Customer updatePartial(
	  String customerId,
	  CustomerPartialUpdateInput customerUpdateInput
	) throws ResourceNotFoundException {
		CustomerEntity customer = this.jpaCustomerRepository.findByIdAndIsDeletedFalse(customerId)
		  .orElseThrow(() -> new ResourceNotFoundException("Customer %s not found".formatted(customerId)));

		return this.customerMapper.map(
		  this.jpaCustomerRepository.save(
			this.customerEntityUpdater.update(customer, customerUpdateInput)
		  )
		);
	}

	@Override
	public void updatePassword(
	  String customerId,
	  String newHashPassword
	) throws ResourceNotFoundException {
		CustomerEntity customerEntity = this.jpaCustomerRepository.findByIdAndIsDeletedFalse(customerId)
		  .orElseThrow(() -> new ResourceNotFoundException("Customer %s not found".formatted(customerId)));

		customerEntity.setEncryptedPassword(newHashPassword);
		customerEntity.setUpdatedAt(java.time.LocalDateTime.now());

		this.jpaCustomerRepository.save(customerEntity);
	}

	@Override
	public PaginationModel<Customer> getAll(PaginationFilters filters) {
		Page<CustomerEntity> customerPage = this.jpaCustomerRepository.findAllByIsDeletedFalse(
		  PageRequest.of(
			filters.getPage(),
			filters.getSize(),
			Sort.by(Sort.Direction.valueOf(filters.getSort().name()), SORT_BY_ID)
		  )
		);

		return new PaginationModel<>(
		  new Pagination(
			customerPage.getNumber(),
			customerPage.getSize(),
			customerPage.getTotalElements(),
			customerPage.getTotalPages(),
			customerPage.isLast()
		  ),
		  customerPage.getContent()
			.stream()
			.map(this.customerMapper::map)
			.toList()
		);
	}

	@Override
	public void delete(String customerId) throws ResourceNotFoundException {
		CustomerEntity customerEntity = this.jpaCustomerRepository.findByIdAndIsDeletedFalse(customerId)
		  .orElseThrow(() -> new ResourceNotFoundException("Customer %s not found".formatted(customerId)));

		customerEntity.setIsDeleted(Boolean.TRUE);
		customerEntity.setDeletedAt(LocalDateTime.now());

		this.jpaCustomerRepository.save(customerEntity);
	}

}
