package com.devsu.customer_service.application.controller.customer.update_credentials;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerCredentialsUpdateRequest {

	@JsonProperty("old_password")
	@Schema(description = "${customer.oldPassword.description}", example = "Gl0r1@.123")
	@NotBlank(message = "customer.password.notBlank")
	@Pattern(
	  regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$",
	  message = "customer.password.invalidFormat"
	)
	private String oldPassword;

	@JsonProperty("new_password")
	@Schema(description = "${customer.newPassword.description}", example = "Gl0r1@.123")
	@NotBlank(message = "customer.password.notBlank")
	@Pattern(
	  regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$",
	  message = "customer.password.invalidFormat"
	)
	private String newPassword;


}
