package com.devsu.customer_service.domain.models;


import com.devsu.customer_service.application.controller.customer.create.CustomerCreateRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerCreateRequestValidationTest {

	private static ValidatorFactory factory;
	private static Validator validator;

	@BeforeAll
	static void setUpValidator() {
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	static void tearDown() {
		if (factory != null) {
			factory.close();
		}
	}

	private void assertViolation(
	  Set<ConstraintViolation<CustomerCreateRequest>> violations,
	  String property,
	  String message
	) {
		boolean found = violations.stream()
		  .anyMatch(v -> v.getPropertyPath().toString().equals(property) && v.getMessage().equals(message));
		assertTrue(
		  found,
		  "Expected violation for property '" + property + "' with message '" + message + "'. Actual violations: " + violations
		);
	}

	private CustomerCreateRequest validRequest() {
		return new CustomerCreateRequest()
		  .setClientId("XEXX010101000")
		  .setName("Gloria Leyva")
		  .setGender(Gender.FEMALE)
		  .setAge(35)
		  .setAddress("Av. Siempre Viva 742, Springfield")
		  .setPhone("+525512345678")
		  .setPassword("Gl0r1@.123");
	}

	@Test
	void validRequest_shouldHaveNoViolations() {
		CustomerCreateRequest req = validRequest();
		Set<ConstraintViolation<CustomerCreateRequest>> violations = validator.validate(req);
		assertTrue(violations.isEmpty(), "Expected no violations for a valid request, but found: " + violations);
	}

	// clientId
	@Test
	void clientId_notBlank_shouldTrigger() {
		CustomerCreateRequest req = validRequest().setClientId(null); // null para aislar NotBlank
		Set<ConstraintViolation<CustomerCreateRequest>> violations = validator.validate(req);
		assertViolation(violations, "clientId", "customer.clientId.notBlank");
	}

	@Test
	void clientId_maxSize_shouldTrigger() {
		String longClientId = "a".repeat(256); // 256 > 255
		CustomerCreateRequest req = validRequest().setClientId(longClientId);
		Set<ConstraintViolation<CustomerCreateRequest>> violations = validator.validate(req);
		assertViolation(violations, "clientId", "customer.clientId.max");
	}

	// name
	@Test
	void name_notBlank_shouldTrigger() {
		CustomerCreateRequest req = validRequest().setName(null);
		Set<ConstraintViolation<CustomerCreateRequest>> violations = validator.validate(req);
		assertViolation(violations, "name", "customer.name.notBlank");
	}

	@Test
	void name_maxSize_shouldTrigger() {
		String longName = "a".repeat(101); // 101 > 100
		CustomerCreateRequest req = validRequest().setName(longName);
		Set<ConstraintViolation<CustomerCreateRequest>> violations = validator.validate(req);
		assertViolation(violations, "name", "customer.name.max");
	}

	// gender
	@Test
	void gender_notNull_shouldTrigger() {
		CustomerCreateRequest req = validRequest().setGender(null);
		Set<ConstraintViolation<CustomerCreateRequest>> violations = validator.validate(req);
		assertViolation(violations, "gender", "customer.gender.null");
	}

	// age
	@Test
	void age_min_shouldTrigger() {
		CustomerCreateRequest req = validRequest().setAge(17); // < 18
		Set<ConstraintViolation<CustomerCreateRequest>> violations = validator.validate(req);
		assertViolation(violations, "age", "customer.age.min");
	}

	// address
	@Test
	void address_notBlank_shouldTrigger() {
		CustomerCreateRequest req = validRequest().setAddress(null);
		Set<ConstraintViolation<CustomerCreateRequest>> violations = validator.validate(req);
		assertViolation(violations, "address", "customer.address.notBlank");
	}

	@Test
	void address_maxSize_shouldTrigger() {
		String longAddress = "a".repeat(256); // 256 > 255
		CustomerCreateRequest req = validRequest().setAddress(longAddress);
		Set<ConstraintViolation<CustomerCreateRequest>> violations = validator.validate(req);
		assertViolation(violations, "address", "customer.address.max");
	}

	// phone
	@Test
	void phone_pattern_shouldTrigger_forInvalidPhone() {
		CustomerCreateRequest req = validRequest().setPhone("qw12dsd"); // no cumple regex (no inicia con +)
		Set<ConstraintViolation<CustomerCreateRequest>> violations = validator.validate(req);
		assertViolation(violations, "phone", "customer.phone.pattern");
	}

	@Test
	void phone_pattern_shouldTrigger_forLeadingZeroAfterPlus() {
		CustomerCreateRequest req = validRequest().setPhone("+0123456789"); // primer dígito no puede ser 0
		Set<ConstraintViolation<CustomerCreateRequest>> violations = validator.validate(req);
		assertViolation(violations, "phone", "customer.phone.pattern");
	}

	// password
	@Test
	void password_notBlank_shouldTrigger() {
		CustomerCreateRequest req = validRequest().setPassword(null); // null para aislar NotBlank
		Set<ConstraintViolation<CustomerCreateRequest>> violations = validator.validate(req);
		assertViolation(violations, "password", "customer.password.notBlank");
	}

	@Test
	void password_pattern_shouldTrigger_forInvalidFormat() {
		// no tiene caracter especial (o no cumple alguna de las reglas del regexp)
		CustomerCreateRequest req = validRequest().setPassword("Password1");
		Set<ConstraintViolation<CustomerCreateRequest>> violations = validator.validate(req);
		assertViolation(violations, "password", "customer.password.invalidFormat");
	}
}