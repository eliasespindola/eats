package br.com.delivery.eats.order.domain.application.ports.output;


public interface ProducerEventPort<T> {

    void execute(T t);
}
