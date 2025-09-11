package com.devsu.customer_service.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
  name = "customers",
  uniqueConstraints = {
	@UniqueConstraint(name = "uk_customer_client_id", columnNames = "client_id")
  }
)
@PrimaryKeyJoinColumn(name = "id")
public class CustomerEntity extends PersonEntity {

	@Column(name = "client_id", nullable = false)
	private String clientId;

	@Column(name = "encrypted_password", length = 100, nullable = false)
	private String encryptedPassword;

}

