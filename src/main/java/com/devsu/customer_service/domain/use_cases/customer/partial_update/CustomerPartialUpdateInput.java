package com.devsu.customer_service.domain.use_cases.customer.partial_update;

import com.devsu.customer_service.domain.models.Gender;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Accessors(chain = true)
public class CustomerPartialUpdateInput {

	private String clientId;
	private String name;
	private Gender gender;
	private Integer age;
	private String address;
	private String phone;

	public Optional<String> getClientId() {
		return Optional.ofNullable(clientId);
	}

	public Optional<String> getName() {
		return Optional.ofNullable(name);
	}

	public Optional<Gender> getGender() {
		return Optional.ofNullable(gender);
	}

	public Optional<Integer> getAge() {
		return Optional.ofNullable(age);
	}

	public Optional<String> getAddress() {
		return Optional.ofNullable(address);
	}

	public Optional<String> getPhone() {
		return Optional.ofNullable(phone);
	}

}
