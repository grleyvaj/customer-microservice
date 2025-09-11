package com.devsu.customer_service.infrastructure.services;

import com.devsu.customer_service.domain.services.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class PasswordEncoderService implements PasswordService {

	private final PasswordEncoder encoder;

	@Override
	public String hash(String plainPassword) {
		return this.encoder.encode(plainPassword);
	}

	@Override
	public boolean verify(String plainPassword, String hashedPassword) {
		return this.encoder.matches(plainPassword, hashedPassword);
	}

}
