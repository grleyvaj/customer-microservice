package com.devsu.customer_service.domain.services;

public interface PasswordService {

	String hash(String plainPassword);

	boolean verify(String plainPassword, String hashedPassword);

}
