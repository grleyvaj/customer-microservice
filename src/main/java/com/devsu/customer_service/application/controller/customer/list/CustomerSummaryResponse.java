package com.devsu.customer_service.application.controller.customer.list;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerSummaryResponse {

	@JsonProperty("id")
	@Schema(description = "${customer.id.description}", example = "01K4SA5VMMYFDQM9C145SZGPW5")
	@NotBlank(message = "customer.id.notBlank")
	private String id;

	@JsonProperty("name")
	@Schema(description = "${customer.name.description}", example = "Gloria Leyva")
	@NotBlank(message = "customer.name.notBlank")
	@Size(max = 100, message = "customer.name.max")
	private String name;

	@JsonProperty("address")
	@Schema(description = "${customer.address.description}", example = "Av. Siempre Viva 742, Springfield")
	@NotBlank(message = "customer.address.notBlank")
	@Size(max = 255, message = "customer.address.max")
	private String address;

	@JsonProperty("phone")
	@Schema(description = "${customer.phone.description}", example = "+525512345678")
	@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "customer.phone.pattern")
	private String phone;

	@JsonProperty("password_status")
	@Schema(description = "${customer.password.description}", example = "Gl0r1@.123")
	@NotBlank(message = "customer.password.notBlank")
	private String encryptedPassword;

	@JsonProperty("state")
	@Schema(description = "${customer.state.description}", example = "True")
	@NotBlank(message = "customer.state.notBlank")
	private boolean state;

}
