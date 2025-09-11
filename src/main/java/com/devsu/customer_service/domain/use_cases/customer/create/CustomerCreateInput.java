package com.devsu.customer_service.domain.use_cases.customer.create;

import com.devsu.customer_service.domain.models.Gender;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerCreateInput {

	private final String clientId;
	private final String name;
	private final Gender gender;
	private final int age;
	private final String address;
	private final String phone;
	private final String password;

	private String encryptedPassword;
	public CustomerCreateInput addEncryptedPassword(String encrypted) {
		this.encryptedPassword = encrypted;
		return this;
	}

}
