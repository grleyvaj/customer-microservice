package com.devsu.customer_service.application.beans;

import com.devsu.customer_service.domain.services.PasswordService;
import com.devsu.customer_service.infrastructure.services.PasswordEncoderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Services {

	@Bean
	public PasswordService passwordService(PasswordEncoder encoder) {
		return new PasswordEncoderService(encoder);
	}

}
