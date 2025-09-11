package com.devsu.customer_service.domain.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Accessors(chain = true)
@Getter
@Setter
public class Customer extends Person {

	private final String clientId;

	public Customer(
	  String id,
	  String clientId,
	  String name,
	  Gender gender,
	  int age,
	  String address,
	  String phone,
	  String encryptedPassword,
	  Boolean isActive,
	  Boolean isDeleted,
	  LocalDateTime createdAt
	) {
		super(id, name, gender, age, address, phone, encryptedPassword, isActive, isDeleted, createdAt);
		this.clientId = clientId;
	}

}
