package com.devsu.customer_service.infrastructure.rabbitmq.customer.delete;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CustomerDeletedMessage implements Serializable {

	@JsonProperty("client_id")
	private final String clientId;

	@JsonProperty("deleted_at")
	private final LocalDateTime deletedAt;

}
