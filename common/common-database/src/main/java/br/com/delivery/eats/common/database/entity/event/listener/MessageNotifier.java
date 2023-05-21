package br.com.delivery.eats.common.database.entity.event.listener;

public interface MessageNotifier<T> {

    void notify(final T source);

}
