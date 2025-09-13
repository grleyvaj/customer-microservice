package com.devsu.customer_service.application.configuration.message;

import lombok.Data;

@Data
public class RetryPropertiesConfig {

	private int maxAttempts;
	private int backoffIntervalMillis;

}
