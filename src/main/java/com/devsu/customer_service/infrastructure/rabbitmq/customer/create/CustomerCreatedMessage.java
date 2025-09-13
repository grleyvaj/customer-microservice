package com.devsu.customer_service.infrastructure.rabbitmq.customer.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CustomerCreatedMessage implements Serializable {

	@JsonProperty("client_id")
	private final String clientId;

	@JsonProperty("name")
	private final String name;

//	@JsonProperty("created_at")
//	private final LocalDateTime created_at;

}
