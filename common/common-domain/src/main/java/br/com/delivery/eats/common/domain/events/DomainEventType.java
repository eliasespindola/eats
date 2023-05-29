package br.com.delivery.eats.common.domain.events;

public enum DomainEventType {
    ORDER_PENDING_EVENT,
    ORDER_PENDING_PAID,
    ORDER_CANCELLED_EVENT,
    ORDER_CANCELLING_EVENT,
    ORDER_APPROVED_EVENT,
    ORDER_PROCESSING_EVENT
}
