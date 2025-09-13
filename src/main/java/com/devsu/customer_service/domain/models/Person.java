package com.devsu.customer_service.domain.models;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Person {

	private final String id;
	private final String name;
	private final Gender gender;
	private final int age;
	private final String address;
	private final String phone;
	private final String encryptedPassword;
	private final Boolean isActive;
	private final Boolean isDeleted;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

}
