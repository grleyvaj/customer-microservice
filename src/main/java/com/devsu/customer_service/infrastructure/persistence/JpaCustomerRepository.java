package com.devsu.customer_service.infrastructure.persistence;

import com.devsu.customer_service.infrastructure.entity.CustomerEntity;
import com.devsu.customer_service.infrastructure.entity.CustomerIdProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, String> {

	boolean existsByClientIdAndIsDeletedFalse(String clientId);

	Optional<CustomerEntity> findByIdAndIsDeletedFalse(String id);

	/*@Query("select c.id from CustomerEntity c where c.clientId = :clientId and c.isDeleted = false")
	Optional<String> findIdByClientIdAndIsDeletedFalse(@Param("clientId") String clientId);*/
	Optional<CustomerIdProjection> findByClientIdAndIsDeletedFalse(String clientId);

	Page<CustomerEntity> findAllByIsDeletedFalse(Pageable pageable);

}
