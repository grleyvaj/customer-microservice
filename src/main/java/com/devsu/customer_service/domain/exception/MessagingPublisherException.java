package com.devsu.customer_service.domain.exception;

public class MessagingPublisherException extends Exception {

	public MessagingPublisherException(String message, Exception ex) {
		super(message, ex);
	}

}
