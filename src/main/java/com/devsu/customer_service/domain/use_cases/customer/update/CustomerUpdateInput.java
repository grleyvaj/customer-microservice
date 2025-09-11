package com.devsu.customer_service.domain.use_cases.customer.update;

import com.devsu.customer_service.domain.models.Gender;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerUpdateInput {

	private final String clientId;
	private final String name;
	private final Gender gender;
	private final int age;
	private final String address;
	private final String phone;

}
