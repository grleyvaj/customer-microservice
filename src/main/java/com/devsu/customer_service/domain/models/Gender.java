package com.devsu.customer_service.domain.models;

public enum Gender {

	MALE("Male"),
	FEMALE("Female"),
	NON_BINARY("Non-binary"),
	OTHER("Other"),
	UNDISCLOSED("Undisclosed");

	private final String name;

	Gender(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
