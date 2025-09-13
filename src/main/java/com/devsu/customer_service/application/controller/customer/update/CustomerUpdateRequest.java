package com.devsu.customer_service.application.controller.customer.update;

import com.devsu.customer_service.domain.models.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerUpdateRequest {

	@JsonProperty("client_id")
	@Schema(description = "${customer.clientId.description}", example = "XEXX010101000")
	@NotBlank(message = "customer.clientId.notBlank")
	private String clientId;

	@JsonProperty("name")
	@Schema(description = "${customer.name.description}", example = "Gloria Leyva")
	@NotBlank(message = "customer.name.notBlank")
	@Size(max = 100, message = "customer.name.max")
	private String name;

	@JsonProperty("gender")
	@Schema(description = "${customer.gender.description}", example = "FEMALE")
	@NotNull(message = "customer.gender.null")
	private Gender gender;

	@JsonProperty("age")
	@Schema(description = "${customer.age.description}", example = "35")
	@Min(value = 18, message = "customer.age.min")
	private int age;

	@JsonProperty("address")
	@Schema(description = "${customer.address.description}", example = "Av. Siempre Viva 742, Springfield")
	@NotBlank(message = "customer.address.notBlank")
	@Size(max = 255, message = "customer.address.max")
	private String address;

	@JsonProperty("phone")
	@Schema(description = "${customer.phone.description}", example = "+525512345678")
	@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message ="customer.phone.pattern")
	private String phone;

}
