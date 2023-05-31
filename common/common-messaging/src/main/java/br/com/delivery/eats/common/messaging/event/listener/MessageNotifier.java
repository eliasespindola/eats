package br.com.delivery.eats.common.messaging.event.listener;

public interface MessageNotifier<T> {

    void notify(final T source, String correlationId);

}
