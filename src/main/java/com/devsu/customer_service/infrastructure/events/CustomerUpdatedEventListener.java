package com.devsu.customer_service.infrastructure.events;

import com.devsu.customer_service.domain.event.CustomerUpdatedDomainEvent;
import com.devsu.customer_service.domain.services.CustomerPublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
public class CustomerUpdatedEventListener {

	private final CustomerPublisherService publisherService;
	private final RetryTemplate retryTemplate;

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Async
	public void handleCustomerUpdated(CustomerUpdatedDomainEvent event) {
		log.info("AFTER_COMMIT - publishing customer updated event for clientId={}", event.getClientId());

		try {
			this.retryTemplate.execute(
			  context -> {
				  log.info(
					"AFTER_COMMIT - publishing customer updated event for clientId={}, attempt={}",
					event.getClientId(),
					context.getRetryCount() + 1
				  );
				  this.publisherService.publishCustomerUpdated(event.getCustomer());
				  return null;
			  }, recoveryContext -> {
				  // Se agotaron los reintentos: lógica de fallback
				  Throwable lastThrowable = recoveryContext.getLastThrowable();
				  log.error(
					"Exhausted retries publishing customer updated event for clientId={} - last error: {}",
					event.getClientId(), lastThrowable == null ? "unknown" : lastThrowable.getMessage(), lastThrowable
				  );

				  // Opcional: persistir en tabla outbox/retry para reintento manual/automático posterior
				  // outboxRepository.save( OutboxEvent.ofCustomerUpdated(event.getCustomer(), lastThrowable) );
				  return null;
			  }
			);
		} catch(Exception ex) {
			log.error("Unexpected error while trying to publish event for clientId={}", event.getClientId(), ex);
		}
	}
}