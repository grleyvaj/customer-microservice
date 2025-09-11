package com.devsu.customer_service.application.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Utils {

	@Bean
	public BCryptPasswordEncoder bPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
